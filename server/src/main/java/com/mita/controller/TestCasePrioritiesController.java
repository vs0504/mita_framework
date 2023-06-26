

package com.mita.controller;

import com.mita.specification.TestCasePrioritySpecificationsBuilder;
import com.mita.dto.TestCasePriorityDTO;
import com.mita.exception.ResourceNotFoundException;
import com.mita.mapper.TestCasePriorityMapper;
import com.mita.model.TestCasePriority;
import com.mita.service.TestCasePriorityService;
import com.mita.web.request.TestCasePriorityRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test_case_priorities")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@CrossOrigin
public class TestCasePrioritiesController {

  private final TestCasePriorityService testCasePriorityService;
  private final TestCasePriorityMapper testCasePriorityMapper;

  @RequestMapping(method = RequestMethod.GET)
  public Page<TestCasePriorityDTO> index(TestCasePrioritySpecificationsBuilder builder, Pageable pageable) {
    Specification<TestCasePriority> spec = builder.build();
    Page<TestCasePriority> uploads = testCasePriorityService.findAll(spec, pageable);
    List<TestCasePriorityDTO> uploadDTOS = testCasePriorityMapper.map(uploads.getContent());
    return new PageImpl<>(uploadDTOS, pageable, uploads.getTotalElements());
  }

  @GetMapping("/{id}")
  public TestCasePriorityDTO show(@PathVariable("id") Long id) throws ResourceNotFoundException {
    TestCasePriority testCasePriority = this.testCasePriorityService.find(id);
    return testCasePriorityMapper.map(testCasePriority);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public TestCasePriorityDTO update(@PathVariable("id") Long id, @RequestBody TestCasePriorityRequest request) throws ResourceNotFoundException {
    TestCasePriority testCasePriority = this.testCasePriorityService.find(id);
    this.testCasePriorityMapper.merge(request, testCasePriority);
    testCasePriority = this.testCasePriorityService.update(testCasePriority);
    return testCasePriorityMapper.map(testCasePriority);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TestCasePriorityDTO create(@RequestBody TestCasePriorityRequest request) throws ResourceNotFoundException {
    TestCasePriority testCasePriority = this.testCasePriorityMapper.map(request);
    testCasePriority = this.testCasePriorityService.create(testCasePriority);
    return testCasePriorityMapper.map(testCasePriority);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void destroy(@PathVariable("id") Long id) throws ResourceNotFoundException {
    this.testCasePriorityService.destroy(id);
  }
}
