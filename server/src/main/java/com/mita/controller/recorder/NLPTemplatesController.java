package com.mita.controller.recorder;

import com.mita.specification.NaturalTextActionSpecificationsBuilder;
import com.mita.specification.SearchCriteria;
import com.mita.dto.NaturalTextActionsDTO;
import com.mita.mapper.NaturalTextActionMapper;
import com.mita.mapper.recorder.NLPTemplateMapper;
import com.mita.model.NaturalTextActions;
import com.mita.model.WorkspaceType;
import com.mita.model.recorder.NLPTemplateDTO;
import com.mita.service.NaturalTextActionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Log4j2
@RequestMapping(path = "/os_recorder/v2/nlp_templates", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@CrossOrigin
public class NLPTemplatesController {
    private final NaturalTextActionsService naturalTextActionsService;
    private final NaturalTextActionMapper naturalTextActionMapper;
    private final NLPTemplateMapper nlpTemplateMapper;

    @GetMapping
    public Page<NLPTemplateDTO> index(NaturalTextActionSpecificationsBuilder builder, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        log.info("Request /os_recorder/v2/nlp_templates");
        WorkspaceType workspaceType = null;
        String applicationType = null;
        for (SearchCriteria param : builder.params) {
            if (param.getKey().equals("applicationType")) {
                applicationType = param.getValue().toString();
            }
        }

        switch(applicationType) {
            case "MobileWeb":
                workspaceType = WorkspaceType.MobileWeb;
                break;
            case "WebApplication":
                workspaceType = WorkspaceType.WebApplication;
                break;
            case "AndroidNative":
                workspaceType = WorkspaceType.AndroidNative;
                break;
            case "IOSWeb":
                workspaceType = WorkspaceType.IOSWeb;
                break;
            case "IOSNative":
                workspaceType = WorkspaceType.IOSNative;
                break;
            case "Rest":
                workspaceType = WorkspaceType.Rest;
                break;
        }
        Page<NaturalTextActions> nlActions = naturalTextActionsService.findAllByWorkspaceType(workspaceType, pageable);
        List<NaturalTextActionsDTO> dtos = naturalTextActionMapper.mapDTO(nlActions.getContent());
        List<NLPTemplateDTO> results = nlpTemplateMapper.changeDataToCamelCase(dtos);
        return new PageImpl<>(results, pageable, nlActions.getTotalElements());
    }
}
