package com.mita.controller;

import com.mita.specification.DryTestPlanSpecificationBuilder;
import com.mita.dto.DryTestPlanDTO;
import com.mita.dto.TestPlanResultDTO;
import com.mita.mapper.DryTestPlanMapper;
import com.mita.mapper.TestPlanMapper;
import com.mita.mapper.TestPlanResultMapper;
import com.mita.model.DryTestPlan;
import com.mita.model.TestDevice;
import com.mita.service.AgentExecutionService;
import com.mita.service.DryTestPlanService;
import com.mita.web.request.DryTestPlanRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@RestController
@Log4j2
@RequestMapping(path = "/dry_test_plans", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
@CrossOrigin
public class DryTestPlansController {

  private final DryTestPlanService service;
  private final DryTestPlanMapper mapper;
  private final TestPlanMapper testPlanMapper;
  private final TestPlanResultMapper testPlanResultMapper;
  private final ObjectFactory<AgentExecutionService> agentExecutionServiceObjectFactory;


  @PostMapping
  public TestPlanResultDTO create(@RequestBody @Valid DryTestPlanRequest request) throws Exception {
    log.info("Create Request /dry_test_plans/ with data::" + request);
    DryTestPlan dryTestPlan = this.mapper.map(request);
    dryTestPlan.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    dryTestPlan.setName("Dry run " + new Timestamp(java.lang.System.currentTimeMillis()));
    TestDevice testDevice = this.testPlanMapper.map(request.getTestDevices().get(0));
    testDevice.setTitle(new Timestamp(Calendar.getInstance().getTimeInMillis()).toString());
    testDevice.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    testDevice.setTestPlanId(dryTestPlan.getId());
    dryTestPlan = this.service.create(dryTestPlan, testDevice);
    AgentExecutionService agentExecutionService = agentExecutionServiceObjectFactory.getObject();
    agentExecutionService.setTestPlan(dryTestPlan);
    agentExecutionService.start();
    return testPlanResultMapper.mapTo(agentExecutionService.getTestPlanResult());
  }

  @GetMapping
  public Page<DryTestPlanDTO> index(DryTestPlanSpecificationBuilder builder, Pageable pageable) {
    Specification<DryTestPlan> spec = builder.build();
    Page<DryTestPlan> dryTestPlans = this.service.findAll(spec, pageable);
    List<DryTestPlanDTO> testPlanDTOS =
      mapper.mapList(dryTestPlans.getContent());
    return new PageImpl<>(testPlanDTOS, pageable, dryTestPlans.getTotalElements());
  }


}
