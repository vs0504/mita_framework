package com.mita.mapper.recorder;

import com.mita.dto.TestCaseDTO;
import com.mita.dto.TestDataProfileDTO;
import com.mita.model.recorder.TestDataDTO;
import com.mita.model.recorder.TestDataSetDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TestDataMapper {

    @Mapping(target = "applicationVersionId", source = "workspaceVersionId")
    @Mapping(target = "isTestComponent", source = "isStepGroup")
    @Mapping(target = "testData", expression = "java(mapDTO(testCaseDTO.getTestData()))")
    com.mita.model.recorder.TestCaseDTO mapTestCaseDTO(TestCaseDTO testCaseDTO);

    List<com.mita.model.recorder.TestCaseDTO> mapTestCaseDTOs(List<TestCaseDTO> testCaseDTO);

    @Mapping(target = "updatedById", ignore = true)
    @Mapping(target = "passwords", ignore = true)
    @Mapping(target = "createdById", ignore = true)
    @Mapping(target = "columns", ignore = true)
    @Mapping(target = "data", expression = "java(getDataForRecorder(testDataProfileDTO.getData()))")
    TestDataDTO mapDTO(TestDataProfileDTO testDataProfileDTO);

    List<TestDataDTO> mapDTOs(List<TestDataProfileDTO> testDataProfileDTOs);

    @Mapping(target = "testDataProfileId", source = "testDataSetDTO.testDataId")
    TestDataSetDTO mapTestDataSetDTO(com.mita.dto.TestDataSetDTO testDataSetDTO);

    @Mapping(target = "testDataProfileId", source = "testDataSetDTO.testDataId")
    List<TestDataSetDTO> mapTestDataSetDTOs(List<com.mita.dto.TestDataSetDTO> testDataSetDTO);

    @Mapping(target = "testDataProfileId", source = "testDataId")
    List<TestDataSetDTO> getDataForRecorder(List<com.mita.dto.TestDataSetDTO> testDataSetDTOs);
}
