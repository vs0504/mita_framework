

package com.mita.mapper;

import com.mita.dto.TestCaseDataDrivenResultDTO;
import com.mita.dto.TestCaseResultDTO;
import com.mita.model.TestCaseDataDrivenResult;
import com.mita.model.TestCaseResult;
import com.mita.web.request.TestCaseResultRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TestCaseDataDrivenResultMapper {

  @Mapping(target = "testCaseResultId", source = "parentId")
  @Mapping(target = "iterationResultId", source = "id")
  @Mapping(target = "testDataName", source = "testDataSetName")
  TestCaseDataDrivenResult map(TestCaseResultRequest testCaseResult);

  @Mapping(target = "iterationResult.testSuite", ignore = true)
  @Mapping(target = "iterationResult.testCase", ignore = true)
  @Mapping(target = "iterationResult.parentResult.testSuite", ignore = true)
  @Mapping(target = "iterationResult.parentResult.testDeviceResult", ignore = true)
  @Mapping(target = "iterationResult.parentResult.testCase", ignore = true)
  @Mapping(target = "iterationResult.parentResult.parentResult", ignore = true)
  @Mapping(target = "iterationResult.testDeviceResult", ignore = true)
  TestCaseDataDrivenResultDTO mapDTO(TestCaseDataDrivenResult testCaseDataDrivenResult);

  List<TestCaseDataDrivenResultDTO> mapDTO(List<TestCaseDataDrivenResult> testCaseDataDrivenResult);

  @Mapping(target = "testCase", ignore = true)
  @Mapping(target = "testDeviceResult", ignore = true)
  @Mapping(target = "testSuite", ignore = true)
  @Mapping(target = "parentResult.testSuite", ignore = true)
  @Mapping(target = "parentResult.testDeviceResult", ignore = true)
  @Mapping(target = "parentResult.testCase", ignore = true)
  @Mapping(target = "parentResult.childResult", ignore = true)
  TestCaseResultDTO mapDTO(TestCaseResult result);
}
