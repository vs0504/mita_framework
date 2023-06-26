package com.mita.controller.recorder;

import com.mita.specification.ApplicationSpecificationsBuilder;
import com.mita.mapper.WorkspaceMapper;
import com.mita.mapper.recorder.ApplicationMapper;
import com.mita.model.Workspace;
import com.mita.model.recorder.ApplicationDTO;
import com.mita.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/os_recorder/applications")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class ApplicationController {

    private final ApplicationMapper applicationMapper;
    private final WorkspaceService workspaceService;
    private final WorkspaceMapper workspaceMapper;

    @GetMapping
    public Page<ApplicationDTO> index(ApplicationSpecificationsBuilder builder,
                                      Pageable pageable) {
        log.info("Request /os_recorder/applications");
        Specification<Workspace> spec = builder.build();
        Page<Workspace> versions = workspaceService.findAll(spec, pageable);
        List<ApplicationDTO> dtos = applicationMapper.mapDTOs(workspaceMapper.map(versions.getContent()));
        return new PageImpl<>(dtos, pageable, versions.getTotalElements());
    }
}
