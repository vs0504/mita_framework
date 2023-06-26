

package com.mita.mapper;

import com.mita.dto.api.APITestSuiteResultDTO;
import com.mita.dto.TestDeviceResultDTO;
import com.mita.dto.TestSuiteEntityDTO;
import com.mita.dto.TestSuiteResultDTO;
import com.mita.model.TestDeviceResult;
import com.mita.model.TestSuiteResult;
import com.mita.web.request.TestSuiteResultRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TestSuiteResultMapper {

  @Mapping(target = "duration", expression = "java( (testCaseGroupResultRequest.getEndTime() != null &&  " +
    "testCaseGroupResultRequest.getStartTime() != null )  ?  testCaseGroupResultRequest.getEndTime().getTime() - " +
    " testCaseGroupResultRequest.getStartTime().getTime() : 0l )")
  @Mapping(target = "status", ignore = true)
  void merge(TestSuiteResultRequest testCaseGroupResultRequest, @MappingTarget TestSuiteResult testCaseGroupResult);

  @Mapping(target = "id", source = "testSuiteResult.testSuite.id")
  @Mapping(target = "name", source = "testSuiteResult.testSuite.name")
  @Mapping(target = "resultId", source = "testSuiteResult.id")
  @Mapping(target = "preRequisite", source = "testSuiteResult.testSuite.preRequisite")
  TestSuiteEntityDTO map(TestSuiteResult testSuiteResult);

  @Mapping(target = "testSuite.lastRun.testSuite", ignore = true)
  @Mapping(target = "testSuite.preRequisiteSuite.lastRun", ignore = true)
  TestSuiteResultDTO mapDTO(TestSuiteResult testSuiteResult);

  List<TestSuiteResultDTO> mapDTO(List<TestSuiteResult> testSuiteResult);
  List<APITestSuiteResultDTO> mapApiDTO(List<TestSuiteResult> testSuiteResult);

  @Mapping(target = "testPlanResult.testPlan.lastRun", ignore = true)
  TestDeviceResultDTO mapDTO(TestDeviceResult testDeviceResult);
}

