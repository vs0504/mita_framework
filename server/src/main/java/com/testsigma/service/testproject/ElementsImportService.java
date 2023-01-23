package com.testsigma.service.testproject;

import com.testsigma.exception.ResourceNotFoundException;
import com.testsigma.model.*;
import com.testsigma.service.ElementScreenService;
import com.testsigma.service.ElementService;
import com.testsigma.service.EntityExternalMappingService;
import com.testsigma.web.request.testproject.TestProjectElementRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
public class ElementsImportService extends BaseImportService<TestProjectElementRequest> {

    private final EntityExternalMappingService entityExternalMappingService;
    private final ElementService elementService;
    private final ElementScreenService elementScreenService;

    Element createElementObject(TestProjectElementRequest elementRequest, Long workspaceVersionId, Integrations integration) throws ResourceNotFoundException {
        Optional<Element> optionalElement = elementService.findByNameAndWorkspaceVersionId(elementRequest.getName(), workspaceVersionId);
        Element element;
        if(optionalElement.isEmpty()) {
            element = new Element();
        } else {
            element = optionalElement.get();
        }
        element.setName(elementRequest.getName());
        element.setWorkspaceVersionId(workspaceVersionId);
        element.setLocatorType(elementRequest.getLocators().get(0).getLocatorType());
        element.setLocatorValue(elementRequest.getLocators().get(0).getValue());
        element.setScreenNameId(findOrCreateScreenName(workspaceVersionId).getId());
        element.setCreatedType(ElementCreateType.MANUAL);
        element.setIsDynamic(false);
        return element;
    }

    private ElementScreenName findOrCreateScreenName(Long workspaceVersionId){
        List<ElementScreenName> screenNames = elementScreenService.findAllByWorkspaceVersionId(workspaceVersionId);
        if(screenNames.isEmpty()){
            ElementScreenName screenName = new ElementScreenName();
            screenName.setName(elementScreenService.DEFAULT_SCREEN_NAME);
            screenName.setWorkspaceVersionId(workspaceVersionId);
            return elementScreenService.save(screenName);
        }
        return screenNames.get(0);
    }
}
