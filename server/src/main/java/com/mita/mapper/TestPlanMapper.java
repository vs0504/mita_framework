

package com.mita.mapper;

import com.mita.web.request.TestDeviceSettings;
import com.mita.dto.TestPlanSettingEntityDTO;
import com.mita.dto.TestDeviceSettingsDTO;
import com.mita.dto.TestPlanDTO;
import com.mita.dto.export.TestPlanCloudXMLDTO;
import com.mita.dto.export.TestPlanXMLDTO;
import com.mita.model.AbstractTestPlan;
import com.mita.model.TestDevice;
import com.mita.model.TestPlan;
import com.mita.web.request.TestDeviceRequest;
import com.mita.web.request.TestPlanRequest;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TestPlanMapper {
  TestPlan map(TestPlanRequest testPlanRequest);

  List<TestPlanXMLDTO> mapToXMLDTOList(List<TestPlan> applications);

  @Mapping(target = "environmentId", expression = "java(testPlanRequest.getEnvironmentId())")
  void merge(@MappingTarget TestPlan testPlan, TestPlanRequest testPlanRequest);

  default List<TestDevice> merge(List<TestDeviceRequest> sourceList, List<TestDevice> targetList) {
    List<TestDevice> newList = new ArrayList<>();
    for (TestDeviceRequest environmentRequest : sourceList) {
      TestDevice target = new TestDevice();
      if (environmentRequest.getId() != null) {
        for (TestDevice environment : targetList) {
          if (environmentRequest.getId().equals(environment.getId())) {
            target = environment;
            break;
          }
        }
      }
      merge(target, environmentRequest);
      newList.add(target);
    }
    return newList;
  }


  void merge(@MappingTarget TestDevice environment, TestDeviceRequest request);


  TestDevice map(TestDeviceRequest request);

  com.mita.model.TestDeviceSettings map(TestDeviceSettings request);

  TestDeviceSettingsDTO mapToDTO(com.mita.model.TestDeviceSettings settings);

  TestPlanSettingEntityDTO mapSettings(AbstractTestPlan testPlan);

  List<TestPlanDTO> mapTo(List<TestPlan> testPlans);

  @Mapping(target = "lastRun.testPlan", ignore = true)
  TestPlanDTO mapTo(TestPlan testPlan);

    TestPlan copy(TestPlan execution);

  List<TestPlan> mapTestPlanCloudList(List<TestPlanCloudXMLDTO> readValue);

  List<TestPlan> mapTestPlanList(List<TestPlanXMLDTO> readValue);
}
