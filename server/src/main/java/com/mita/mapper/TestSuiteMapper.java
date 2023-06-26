

package com.mita.mapper;

import com.mita.dto.TestSuiteDTO;
import com.mita.dto.TestSuiteEntityDTO;
import com.mita.dto.export.TestSuiteCloudXMLDTO;
import com.mita.dto.export.TestSuiteXMLDTO;
import com.mita.model.TestSuite;
import com.mita.web.request.TestSuiteRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TestSuiteMapper {
  List<TestSuiteXMLDTO> mapTestSuites(List<TestSuite> testSuites);

  TestSuiteEntityDTO map(TestSuite suite);

  @Mapping(target = "lastRun.testSuite", ignore = true)
  @Mapping(target = "lastRun.testDeviceResult", ignore = true)
  @Mapping(target = "preRequisiteSuite.lastRun", ignore = true)
  TestSuiteDTO mapToDTO(TestSuite suite);

  List<TestSuiteDTO> mapToDTO(List<TestSuite> suite);

  TestSuite map(TestSuiteRequest testSuiteRequest);

  @Mapping(target = "preRequisite", expression = "java(request.getPreRequisite())")
  void merge(TestSuiteRequest request, @MappingTarget TestSuite testSuite);

    TestSuite copy(TestSuite testSuite);

  List<TestSuite> mapCloudTestSuiteList(List<TestSuiteCloudXMLDTO> readValue);

  List<TestSuite> mapTestSuiteList(List<TestSuiteXMLDTO> readValue);
}

