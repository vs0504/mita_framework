

package com.mita.mapper;

import com.mita.dto.TestCaseFilterDTO;
import com.mita.model.TestCaseFilter;
import com.mita.web.request.TestCaseFilterRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TestCaseFilterMapper {
  TestCaseFilter map(TestCaseFilterRequest request);

  void merge(@MappingTarget TestCaseFilter testCaseFilter, TestCaseFilterRequest request);

  List<TestCaseFilterDTO> map(List<TestCaseFilter> testCaseFilters);

  TestCaseFilterDTO map(TestCaseFilter testCaseFilter);

}
