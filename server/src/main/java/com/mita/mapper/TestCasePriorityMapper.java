

package com.mita.mapper;

import com.mita.dto.TestCasePriorityDTO;
import com.mita.dto.export.TestCasePriorityCloudXMLDTO;
import com.mita.dto.export.TestCasePriorityXMLDTO;
import com.mita.model.TestCasePriority;
import com.mita.web.request.TestCasePriorityRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TestCasePriorityMapper {
  List<TestCasePriorityXMLDTO> mapTestCasePriorities(List<TestCasePriority> applications);

  TestCasePriorityDTO map(TestCasePriority testCasePriority);

  List<TestCasePriorityDTO> map(List<TestCasePriority> testCasePriorities);

  TestCasePriority map(TestCasePriorityRequest testCasePriorityRequest);

  void merge(TestCasePriorityRequest testCasePriorityRequest, @MappingTarget TestCasePriority testCasePriority);

  List<TestCasePriority> mapTestcasePriorityList(List<TestCasePriorityXMLDTO> readValue);
  List<TestCasePriority> mapCloudTestcasePriorityList(List<TestCasePriorityCloudXMLDTO> readValue);

  TestCasePriority copy(TestCasePriority testCasePriority);
}
