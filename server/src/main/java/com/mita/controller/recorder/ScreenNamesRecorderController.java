package com.mita.controller.recorder;

import com.mita.dto.ElementScreenNameDTO;
import com.mita.specification.SearchCriteria;
import com.mita.mapper.ElementScreenNameMapper;
import com.mita.mapper.recorder.UiIdentifierMapper;
import com.mita.model.ElementScreenName;
import com.mita.model.recorder.UiIdentifierScreenNameDTO;
import com.mita.model.recorder.UiIdentifierScreenNameRequest;
import com.mita.service.ElementScreenNameSpecificationsBuilder;
import com.mita.service.ElementScreenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/os_recorder/ui_identifiers_screen_name")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class ScreenNamesRecorderController {
    private final ElementScreenService elementScreenService;
    private final ElementScreenNameMapper elementScreenNameMapper;
    private final UiIdentifierMapper uiIdentifierMapper;

    @RequestMapping(method = RequestMethod.GET)
    public Page<UiIdentifierScreenNameDTO> index(ElementScreenNameSpecificationsBuilder builder, Pageable pageable) {
        Long workspaceVersionId = null;
        for (SearchCriteria param : builder.params) {
            if (param.getKey().equals("applicationVersionId") || param.getKey().equals("workspaceVersionId")) {
                workspaceVersionId = Long.parseLong(param.getValue().toString());
            }
        }
        Page<ElementScreenName> elements = elementScreenService.findAllByWorkspaceVersionId(workspaceVersionId, pageable);
        List<ElementScreenNameDTO> elementDTOS = elementScreenNameMapper.map(elements.getContent());
        List<UiIdentifierScreenNameDTO> uiIdentifierScreenNameDTOS = uiIdentifierMapper.mapScreenNameDTOs(elementDTOS);
        return new PageImpl<>(uiIdentifierScreenNameDTOS, pageable, elements.getTotalElements());
    }

    @RequestMapping(method = RequestMethod.POST)
    public UiIdentifierScreenNameDTO save(@RequestBody UiIdentifierScreenNameRequest request) {
        ElementScreenNameDTO result = elementScreenNameMapper.map(elementScreenService.save(uiIdentifierMapper.mapScreenNameRequest(request)));
        return uiIdentifierMapper.mapScreenNameDTO(result);
    }
}

