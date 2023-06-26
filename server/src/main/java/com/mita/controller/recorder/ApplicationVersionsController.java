package com.mita.controller.recorder;

import com.mita.specification.SearchCriteria;
import com.mita.specification.VersionSpecificationsBuilder;
import com.mita.mapper.WorkspaceVersionMapper;
import com.mita.mapper.recorder.ApplicationMapper;
import com.mita.model.WorkspaceVersion;
import com.mita.model.recorder.ApplicationVersionDTO;
import com.mita.service.WorkspaceVersionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/os_recorder/application_versions")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class ApplicationVersionsController {

    private final WorkspaceVersionService workspaceVersionService;
    private final WorkspaceVersionMapper workspaceVersionMapper;
    private final ApplicationMapper applicationMapper;

    @GetMapping
    public Page<ApplicationVersionDTO> index(VersionSpecificationsBuilder builder,
                                             Pageable pageable) {
        log.info("Request /os_recorder/application_versions");
        Long workspaceId = null;
        for (SearchCriteria param : builder.params) {
            if (param.getKey().equals("applicationId") || param.getKey().equals("workspaceId")) {
                workspaceId = Long.parseLong(param.getValue().toString());
            }
        }
        Page<WorkspaceVersion> versions = workspaceVersionService.findByWorkspaceId(workspaceId, pageable);
        List<ApplicationVersionDTO> dtos = applicationMapper.mapApplicationVersionDTOs(workspaceVersionMapper.map(versions.getContent()));
        return new PageImpl<>(dtos, pageable, versions.getTotalElements());
    }
}