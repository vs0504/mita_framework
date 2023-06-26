

package com.mita.mapper;

import com.mita.dto.export.SuiteTestCaseMappingXMLDTO;
import com.mita.model.SuiteTestCaseMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TestSuiteTestCaseMapper {

  List<SuiteTestCaseMappingXMLDTO> map(List<SuiteTestCaseMapping> suiteTestCaseMappingList);

  List<SuiteTestCaseMapping> mapXML(List<SuiteTestCaseMappingXMLDTO> suiteTestCaseMappingList);

  SuiteTestCaseMapping copy(SuiteTestCaseMapping suite);

}

