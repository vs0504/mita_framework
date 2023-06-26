

package com.mita.mapper;

import com.mita.dto.TestCaseTypeDTO;
import com.mita.dto.export.TestCaseTypeCloudXMLDTO;
import com.mita.dto.export.TestCaseTypeXMLDTO;
import com.mita.model.TestCaseType;
import com.mita.web.request.TestCaseTypeRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TestCaseTypeMapper {
  List<TestCaseTypeXMLDTO> mapTestCaseTypes(List<TestCaseType> applications);

  TestCaseTypeDTO map(TestCaseType testCaseType);

  List<TestCaseTypeDTO> map(List<TestCaseType> testCaseTypes);

  TestCaseType map(TestCaseTypeRequest testCaseTypeRequest);

  void merge(TestCaseTypeRequest testCaseTypeRequest, @MappingTarget TestCaseType testCaseType);

  List<TestCaseType> mapTestCaseTypeList(List<TestCaseTypeXMLDTO> readValue);


  TestCaseType copy(TestCaseType testCasePriority);

  List<TestCaseType> mapTestCaseTypeCloudList(List<TestCaseTypeCloudXMLDTO> readValue);
}
