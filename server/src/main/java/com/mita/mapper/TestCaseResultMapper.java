

package com.mita.mapper;

import com.mita.dto.TestCaseEntityDTO;
import com.mita.dto.TestCaseResultDTO;
import com.mita.dto.TestDeviceResultDTO;
import com.mita.dto.TestSuiteDTO;
import com.mita.dto.api.APITestCaseResultDTO;
import com.mita.model.TestCaseResult;
import com.mita.model.TestDeviceResult;
import com.mita.model.TestSuite;
import com.mita.web.request.TestCaseResultRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {TestCaseMapper.class})
public interface TestCaseResultMapper {

  @Mapping(target = "duration", expression = "java((testCaseResultRequest.getEndTime() != null && testCaseResultRequest.getStartTime() != null) ?" +
    " testCaseResultRequest.getEndTime().getTime() -  testCaseResultRequest.getStartTime().getTime() :  0l)")
  @Mapping(target = "status", ignore = true)
  void merge(TestCaseResultRequest testCaseResultRequest,
             @MappingTarget TestCaseResult testCaseResult);

  @Mapping(target = "status", ignore = true)
  @Mapping(target = "id", source = "testCaseId")
  @Mapping(target = "testCaseName", expression = "java(testCaseResult.getTestCase().getName())")
  @Mapping(target = "isStepGroup", source = "isStepGroup")
  @Mapping(target = "preRequisite", expression = "java(testCaseResult.getTestCase().getPreRequisite())")
  @Mapping(target = "isDataDriven", expression = "java(testCaseResult.getTestCase().getIsDataDriven())")
  @Mapping(target = "testDataId", expression = "java(testCaseResult.getTestCase().getTestDataId())")
  @Mapping(target = "testDataStartIndex", expression = "java(testCaseResult.getTestCase().getTestDataStartIndex())")
  @Mapping(target = "testCaseResultId", source = "id")
  @Mapping(target = "startTime", expression = "java(testCaseResult.getCalendarTimeFromTimestamp(testCaseResult.getStartTime()))")
  @Mapping(target = "endTime", expression = "java(testCaseResult.getCalendarTimeFromTimestamp(testCaseResult.getEndTime()))")
  TestCaseEntityDTO map(TestCaseResult testCaseResult);

  List<TestCaseEntityDTO> map(List<TestCaseResult> dataDrivenTestCaseResults);

  @Mapping(target = "testCase.lastRun", ignore = true)
  @Mapping(target = "testCase.results", ignore = true)
  @Mapping(target = "parentResult.childResult", ignore = true)
  @Mapping(target = "testCase.testData", ignore = true)
  TestCaseResultDTO mapDTO(TestCaseResult result);

  List<TestCaseResultDTO> mapDTO(List<TestCaseResult> result);

  @Mapping(target = "testCase.lastRun", ignore = true)
  @Mapping(target = "testCase.results", ignore = true)
  @Mapping(target = "parentResult.childResult", ignore = true)
  @Mapping(target = "testCase.testData", ignore = true)
  APITestCaseResultDTO mapApiDTO(TestCaseResult result);

  List<APITestCaseResultDTO> mapApiDTOs(List<TestCaseResult> result);


  @Mapping(target = "lastRun.testSuite", ignore = true)
  @Mapping(target = "lastRun.testDeviceResult", ignore = true)
  @Mapping(target = "preRequisiteSuite.lastRun", ignore = true)
  TestSuiteDTO mapDTO(TestSuite suite);

  @Mapping(target = "testPlanResult.testPlan.lastRun", ignore = true)
  TestDeviceResultDTO mapDTO(TestDeviceResult testDeviceResult);
}

