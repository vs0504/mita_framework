/*
 *
 * ****************************************************************************
 *  * Copyright (C) 2019 Testsigma Technologies Inc.
 *  * All rights reserved.
 *  ****************************************************************************
 *
 */

package com.testsigma.controller;

import com.testsigma.constants.AutomatorMessages;
import com.testsigma.dto.TestCaseResultDTO;
import com.testsigma.exception.ResourceNotFoundException;
import com.testsigma.mapper.TestCaseResultMapper;
import com.testsigma.model.TestCaseResult;
import com.testsigma.model.WorkspaceVersionMapping;
import com.testsigma.service.TestCaseResultService;
import com.testsigma.service.WorkspaceVersionMappingService;
import com.testsigma.service.testproject.UserOnboardingService;
import com.testsigma.specification.TestCaseResultSpecificationsBuilder;
import com.testsigma.util.XLSUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/test_case_results")
@CrossOrigin
public class TestCaseResultsController {

  private final TestCaseResultService testCaseResultService;
  private final TestCaseResultMapper testCaseResultMapper;
  private final UserOnboardingService userOnboardingService;
  private final WorkspaceVersionMappingService workspaceVersionMappingService;

  @RequestMapping(method = RequestMethod.GET)
  public Page<TestCaseResultDTO> index(TestCaseResultSpecificationsBuilder builder, Pageable pageable) {
    log.info("Request /test_case_results/");
    Specification<TestCaseResult> spec = builder.build();
    Page<TestCaseResult> testCaseResults = testCaseResultService.findAll(spec, pageable);
    List<TestCaseResultDTO> testSuiteResultDTOs =
      testCaseResultMapper.mapDTO(testCaseResults.getContent());

    Long userId = userOnboardingService.getCurrentUserId();
    List<WorkspaceVersionMapping> workspaceVersionMappingList= workspaceVersionMappingService.findWorkSpaceVersionByUserId1(userId);

    List<TestCaseResultDTO> finalResultDtos= new ArrayList<>();
    for(TestCaseResultDTO testCaseResultDTO: testSuiteResultDTOs){
      List<WorkspaceVersionMapping> workspaceVersionMappingTemp = workspaceVersionMappingList.stream().filter(testCases -> testCases.getWorkspaceVersionId().toString().equals(testCaseResultDTO.getTestCase().getWorkspaceVersionId().toString())).collect(Collectors.toList());
      if(workspaceVersionMappingTemp.size()>0&&workspaceVersionMappingTemp.get(0).getStatus().equalsIgnoreCase("ACTIVE"))
        finalResultDtos.add(testCaseResultDTO);
    }
    return new PageImpl<>(finalResultDtos, pageable, finalResultDtos.size());
  }

  @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
  public TestCaseResultDTO show(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
    log.info("Request /test_case_results/" + id);
    TestCaseResult testCaseResult = testCaseResultService.find(id);
    if(testCaseResult.getMessage() != null && testCaseResult.getMessage().contains(AutomatorMessages.MSG_INCOMPATIBLE_DEVICE_AND_APP)) {
      testCaseResult.setMessage("Architecture is unsupported for the selected application. If running on Simulator, try uploading a simulator build");
    }
    return testCaseResultMapper.mapDTO(testCaseResult);
  }

  @GetMapping(value = "/export/{id}")
  @PreAuthorize("hasPermission('RESULTS','READ')")
  public void exportRunResults(
          HttpServletRequest request,
          @PathVariable(value = "id") Long id,
          HttpServletResponse response) throws ResourceNotFoundException {
    XLSUtil wrapper = new XLSUtil();
    TestCaseResult testCaseResult = testCaseResultService.find(id);
    String testCaseName = testCaseResult.getIteration() != null ? testCaseResult.getIteration() : testCaseResult.getTestCase().getName();
    testCaseResultService.export(testCaseResult, wrapper);
    wrapper.writeToStream(request, response, testCaseName);
  }
}
