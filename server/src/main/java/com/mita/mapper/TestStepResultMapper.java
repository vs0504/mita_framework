

package com.mita.mapper;


import com.mita.dto.ElementDTO;
import com.mita.dto.ElementMetaDataDTO;
import com.mita.dto.StepResultMetadataDTO;
import com.mita.dto.TestStepResultDTO;
import com.mita.model.*;
import com.mita.web.request.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TestStepResultMapper {

  ElementMetaDataDTO mapMetaData(ElementMetaData elementMetaData);

  @Mapping(target = "currentElement", expression = "java(elementMetaDataDTO.getCurrentElement().toString())")
  ElementMetaData mapMetaData(ElementMetaDataDTO elementMetaDataDTO);

  Element mapFrom(ElementDTO elementDTO);

  @Mapping(target = "metadata",
    expression = "java(this.mapMetadata(testCaseStepResult.getMetadata()))")
  @Mapping(target = "duration", expression = "java(testCaseStepResult.getEndTime().getTime() - testCaseStepResult" +
    ".getStartTime().getTime())")
  @Mapping(target = "stepId", source = "testCaseStepId")
  @IterableMapping(qualifiedByName = "suggestionRequest")
  TestStepResult map(TestStepResultRequest testCaseStepResult);

  StepDetails map(StepDetailsRequest stepDetails);

  @Mapping(target = "forLoop", expression = "java(this.mapForLoop(metadataRequest.getForLoop()))")
  @Mapping(target = "whileLoop", expression = "java(this.mapWhileLoop(metadataRequest.getWhileLoop()))")
  StepResultMetadata mapMetadata(StepResultMetadataRequest metadataRequest);

  StepResultForLoopMetadata mapForLoop(StepResultForLoopMetadataRequest forLoopMetadataRequest);

  StepResultWhileLoopMetadata mapWhileLoop(StepResultWhileLoopMetadataRequest whileLoopMetadataRequest);

  @Mapping(target = "metadata", expression = "java(this.mapMetadataDTO(testStepResult.getMetadata()))")
  TestStepResultDTO mapDTO(TestStepResult testStepResult);

  StepResultMetadataDTO mapMetadataDTO(StepResultMetadata metadata);

  List<TestStepResultDTO> mapDTO(List<TestStepResult> testStepResult);
}
