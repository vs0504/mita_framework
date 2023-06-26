package com.mita.controller;

import com.mita.dto.ElementScreenNameDTO;
import com.mita.mapper.ElementScreenNameMapper;
import com.mita.model.ElementScreenName;
import com.mita.service.ElementScreenNameSpecificationsBuilder;
import com.mita.service.ElementScreenService;
import com.mita.web.request.ElementScreenNameRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/elements_screen_name")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class ScreenNamesController {
  private final ElementScreenService elementScreenService;
  private final ElementScreenNameMapper elementScreenNameMapper;

  @RequestMapping(method = RequestMethod.GET)
  public Page<ElementScreenNameDTO> index(ElementScreenNameSpecificationsBuilder builder, Pageable pageable) {
    Specification<ElementScreenName> spec = builder.build();
    Page<ElementScreenName> elements = elementScreenService.findAll(spec, pageable);
    List<ElementScreenNameDTO> elementDTOS = elementScreenNameMapper.map(elements.getContent());
    return new PageImpl<>(elementDTOS, pageable, elements.getTotalElements());
  }

  @RequestMapping(method = RequestMethod.POST)
  public ElementScreenNameDTO save(@RequestBody ElementScreenNameRequest request) {
    return elementScreenNameMapper.map(elementScreenService.save(request));
  }
}
