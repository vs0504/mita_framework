package com.mita.controller.api.v1;

import com.mita.specification.TestCaseResultSpecificationsBuilder;
import com.mita.dto.api.APITestCaseResultDTO;
import com.mita.mapper.TestCaseResultMapper;
import com.mita.model.TestCaseResult;
import com.mita.service.TestCaseResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController(value = "apiTestCaseResultController")
@RequestMapping(path = "/api/v1/test_case_results")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@CrossOrigin
public class TestCaseResultController {

  private final TestCaseResultService testCaseResultService;
  private final TestCaseResultMapper testCaseResultMapper;

  @GetMapping
  public Page<APITestCaseResultDTO> index(TestCaseResultSpecificationsBuilder builder, @PageableDefault(size = 50) Pageable pageable) {
    Specification<TestCaseResult> spec = builder.build();
    Page<TestCaseResult> testCaseResults = testCaseResultService.findAll(spec, pageable);
    List<APITestCaseResultDTO> testCaseResultDTOS = testCaseResultMapper.mapApiDTOs(testCaseResults.getContent());
    return new PageImpl<>(testCaseResultDTOS, pageable, testCaseResults.getTotalElements());
  }
}
