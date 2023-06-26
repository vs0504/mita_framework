

package com.mita.controller.api.v1;

import com.mita.specification.ElementSpecificationsBuilder;
import com.mita.dto.api.APIElementDTO;
import com.mita.exception.ResourceNotFoundException;
import com.mita.exception.TestsigmaDatabaseException;
import com.mita.exception.TestsigmaException;
import com.mita.mapper.ElementMapper;
import com.mita.model.Element;
import com.mita.model.LocatorType;
import com.mita.service.ElementService;
import com.mita.web.request.ElementRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController("apiElementsController")
@RequestMapping(path = "/api/v1/elements")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class ElementsController {
  private final ElementService elementService;
  private final ElementMapper elementMapper;

  @RequestMapping(method = RequestMethod.POST)
  public APIElementDTO create(@RequestBody @Valid ElementRequest elementRequest) {
    Element element = elementMapper.map(elementRequest);
    elementService.create(element);
    return elementMapper.mapToApi(element);
  }

  @RequestMapping(method = RequestMethod.GET)
  public Page<APIElementDTO> index(ElementSpecificationsBuilder builder, Pageable pageable) {
    Specification<Element> spec = builder.build();
    Page<Element> elements = elementService.findAll(spec, pageable);
    List<APIElementDTO> elementDTOS = elementMapper.mapToApiList(elements.getContent());
    return new PageImpl<>(elementDTOS, pageable, elements.getTotalElements());
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public APIElementDTO show(@PathVariable("id") Long id) throws ResourceNotFoundException {
    Element element = elementService.find(id);
    return elementMapper.mapToApi(element);
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
  public APIElementDTO update(@PathVariable("id") Long id, @RequestBody ElementRequest elementRequest)
          throws ResourceNotFoundException, TestsigmaDatabaseException {
    Element element = elementService.find(id);
    String oldName = element.getName();
    String previousLocatorValue = element.getLocatorValue();
    Long previousScreenNameId = element.getScreenNameId();
    LocatorType previousLocatorType = element.getLocatorType();
    elementMapper.merge(elementRequest, element);
    elementService.update(element, oldName, previousLocatorValue, previousLocatorType, previousScreenNameId);
    return elementMapper.mapToApi(element);
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("id") Long id) throws TestsigmaException, IOException {
    elementService.delete(elementService.find(id));
  }
}
