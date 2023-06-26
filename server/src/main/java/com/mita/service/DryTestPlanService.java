

package com.mita.service;

import com.mita.exception.ResourceNotFoundException;
import com.mita.repository.DryTestPlanRepository;
import com.mita.model.DryTestPlan;
import com.mita.model.DryTestSuite;
import com.mita.model.TestDevice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DryTestPlanService {

  private final DryTestPlanRepository dryTestPlanRepository;
  private final TestDeviceService testDeviceService;
  private final DryTestSuiteService testSuiteService;

  public Page<DryTestPlan> findAll(Specification<DryTestPlan> spec, Pageable pageable) {
    return dryTestPlanRepository.findAll(spec, pageable);
  }

  public DryTestPlan find(Long testPlanId) throws ResourceNotFoundException {
    return this.dryTestPlanRepository.findById(testPlanId)
      .orElseThrow(() -> new ResourceNotFoundException("Missing Dry execution"));
  }

  public DryTestPlan create(DryTestPlan execution, TestDevice testDevice) throws ResourceNotFoundException {
    execution = this.dryTestPlanRepository.save(execution);
    DryTestSuite testSuite = new DryTestSuite();
    testSuite.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    testSuite.setName(new Timestamp(Calendar.getInstance().getTimeInMillis()).toString());
    testSuite.setWorkspaceVersionId(execution.getWorkspaceVersionId());
    testDevice.setWorkspaceVersionId(execution.getWorkspaceVersionId());
    testSuite.setTestCaseId(execution.getTestCaseId());
    testSuite = testSuiteService.create(testSuite);
    testDevice.setSuiteIds(Collections.singletonList(testSuite.getId()));
    testDevice.setTestPlanId(execution.getId());
    testDeviceService.create(testDevice);
    return find(execution.getId());
  }
}
