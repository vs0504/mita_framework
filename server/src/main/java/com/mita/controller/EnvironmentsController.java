package com.mita.controller;

import com.mita.specification.EnvironmentSpecificationsBuilder;
import com.mita.dto.EnvironmentDTO;
import com.mita.exception.ResourceNotFoundException;
import com.mita.mapper.EnvironmentMapper;
import com.mita.model.Environment;
import com.mita.service.EnvironmentService;
import com.mita.web.request.EnvironmentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/environments")
@CrossOrigin
public class EnvironmentsController {

  private final EnvironmentMapper environmentMapper;
  private final EnvironmentService environmentService;

  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public EnvironmentDTO show(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
    log.info("Get Request /environments/" + id);
    Environment environment = environmentService.find(id);
    EnvironmentDTO environmentDTO = environmentMapper.map(environment);
    return environmentDTO;
  }

  @RequestMapping(method = RequestMethod.GET)
  public Page<EnvironmentDTO> index(EnvironmentSpecificationsBuilder builder, Pageable pageable) {
    log.info("Get Request /environments");
    Specification<Environment> spec = builder.build();
    Page<Environment> environments = environmentService.findAll(spec, pageable);
    List<EnvironmentDTO> environmentDTOS =
      environmentMapper.map(environments.getContent());
    return new PageImpl<>(environmentDTOS, pageable, environments.getTotalElements());
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void destroy(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
    log.info("Delete Request /environments/" + id);
    environmentService.destroy(id);
  }

  @DeleteMapping(value = "/bulk")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void bulkDelete(@RequestParam(value = "ids[]") Long[] ids) throws Exception {
    environmentService.bulkDestroy(ids);
  }

  @PutMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public EnvironmentDTO update(@PathVariable(value = "id") Long id, @RequestBody EnvironmentRequest request) throws ResourceNotFoundException {
    log.info("Update Request /environments/" + id);
    Environment environment = environmentService.find(id);
    Environment oldEnvironment = new Environment();
    oldEnvironment.setData(environment.getData());
    environmentMapper.merge(environment, request);
    environment.setUpdatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    environment = this.environmentService.update(environment);
    return environmentMapper.map(environment);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EnvironmentDTO create(@RequestBody EnvironmentRequest request) throws ResourceNotFoundException {
    log.info("Create Request /environments/" + request);
    Environment environment = environmentMapper.map(request);
    environment.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    environment = this.environmentService.create(environment);
    return environmentMapper.map(environment);
  }
}
