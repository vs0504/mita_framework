package com.mita.mapper.recorder;

import com.mita.service.ObjectMapperService;
import com.mita.dto.TestStepDTO;
import com.mita.model.ResultConstant;
import com.mita.model.TestStepType;
import com.mita.model.recorder.TestStepRecorderDTO;
import com.mita.model.recorder.TestStepRecorderRequest;
import com.mita.web.request.TestStepRequest;
import org.mapstruct.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TestStepRecorderMapper {

    @Mapping(target = "uiIdentifierDTO", ignore = true)
    @Mapping(target = "testComponentId", source = "stepGroupId")
    @Mapping(target = "templateId", source = "naturalTextActionId")
    @Mapping(target = "stepDescription", ignore = true)
    @Mapping(target = "screenShotURL", ignore = true)
    @Mapping(target = "pageSourceUrl", ignore = true)
    @Mapping(target = "pageSource", ignore = true)
    @Mapping(target = "mailBoxId", ignore = true)
    @Mapping(target = "kibbutzPluginNlpId", source = "addonActionId")
    @Mapping(target = "invalidUiIdentifierList", ignore = true)
    @Mapping(target = "invalidTestDataList", ignore = true)
    @Mapping(target = "importedId", ignore = true)
    @Mapping(target = "hasInvalidUiIdentifier", ignore = true)
    @Mapping(target = "hasInvalidTestData", ignore = true)
    @Mapping(target = "dataMap", expression = "java(testStepDTO.mapTestData())")
    @Mapping(target = "componentTestCaseEntity", ignore = true)
    @Mapping(target = "blockId", ignore = true)
    @Mapping(target = "type", expression = "java(gettType(testStepDTO))")
    TestStepRecorderDTO mapDTO(TestStepDTO testStepDTO);

    List<TestStepRecorderDTO> mapDTOs(List<TestStepDTO> testStepDTO);


    @Mapping(target = "ifConditionExpectedResults", expression = "java(mapIfConditionExpectedResults(testStepRecorderRequest))")
    @Mapping(target = "toElement", source = "testStepRecorderRequest.dataMap.fromUiIdentifier")
    @Mapping(target = "testDataType", expression = "java(mapTestDataType(testStepRecorderRequest))")
    @Mapping(target = "testDataFunctionId", expression = "java(mapTestDataFunctionId(testStepRecorderRequest))")
    @Mapping(target = "testDataFunctionArgs", expression = "java(mapTestDataFunctionArgs(testStepRecorderRequest))")
    @Mapping(target = "testData", expression = "java(mapTestDataValue(testStepRecorderRequest))")
    @Mapping(target = "stepGroupId", source = "testComponentId")
    @Mapping(target = "naturalTextActionId", source = "templateId")
    @Mapping(target = "fromElement", source = "testStepRecorderRequest.dataMap.toUiIdentifier")
    @Mapping(target = "forLoopTestDataId", source = "testStepRecorderRequest.dataMap.forLoop.testDataId")
    @Mapping(target = "forLoopStartIndex", source = "testStepRecorderRequest.dataMap.forLoop.startIndex")
    @Mapping(target = "forLoopEndIndex", source = "testStepRecorderRequest.dataMap.forLoop.endIndex")
    @Mapping(target = "element", expression = "java(mapElement(testStepRecorderRequest))")
    @Mapping(target = "attribute", source = "testStepRecorderRequest.dataMap.attribute")
    @Mapping(target = "addonTestData", source = "kibbutzPluginNlpData.testData")
    @Mapping(target = "addonTDF", ignore = true)
    @Mapping(target = "addonNaturalTextActionData", ignore = true)
    @Mapping(target = "addonElements", source = "kibbutzPluginNlpData.uiIdentifiers")
    @Mapping(target = "addonActionId", source = "kibbutzPluginNlpId")
    TestStepRequest mapRequest(TestStepRecorderRequest testStepRecorderRequest);

    default String mapTestDataValue(TestStepRecorderRequest testStepRecorderRequest) {
        Optional<String> data = Optional.ofNullable(testStepRecorderRequest)
                .map(request -> request.getDataMap())
                .map(dataMap -> dataMap.getTestData())
                .map(testData -> testData.values())
                .map(values -> values.stream().findFirst())
                .map(nlpData -> nlpData.get().getValue());
        return data.isPresent() ? data.get() : null;
    }

    default Long mapTestDataFunctionId(TestStepRecorderRequest testStepRecorderRequest) {
        Optional<Long> data = Optional.ofNullable(testStepRecorderRequest)
                .map(request -> request.getDataMap())
                .map(dataMap -> dataMap.getTestData())
                .map(testData -> testData.values())
                .map(values -> values.stream().findFirst())
                .map(nlpData -> nlpData.get().getTestDataFunction())
                .map(testDataRecorderFunction -> testDataRecorderFunction.getId());
        return data.isPresent() ? data.get() : null;
    }

    default Map<String, String> mapTestDataFunctionArgs(TestStepRecorderRequest testStepRecorderRequest) {
        Optional<Map<String, String>> data = Optional.ofNullable(testStepRecorderRequest)
                .map(request -> request.getDataMap())
                .map(dataMap -> dataMap.getTestData())
                .map(testData -> testData.values())
                .map(values -> values.stream().findFirst())
                .map(nlpData -> nlpData.get().getTestDataFunction())
                .map(testDataFunction -> testDataFunction.getTestDataFunctionArgs());
        return data.isPresent() ? data.get() : null;
    }

    default String mapTestDataType(TestStepRecorderRequest testStepRecorderRequest) {
        Optional<String> dataType = Optional.ofNullable(testStepRecorderRequest)
                .map(request -> request.getDataMap())
                .map(dataMap -> dataMap.getTestData())
                .map(testData -> testData.values())
                .map(values -> values.stream().findFirst())
                .map(nlpData -> nlpData.get().getType());
        return dataType.isPresent() ? dataType.get() : null;
    }

    default ResultConstant[] mapIfConditionExpectedResults(TestStepRecorderRequest testStepRecorderRequest) {
        Optional<Object> ifCondtionExpectedResults = Optional.ofNullable(testStepRecorderRequest)
                .map(request -> request.getDataMap())
                .map(dataMap -> dataMap.getIfConditionExpectedResults());

        if(ifCondtionExpectedResults.isPresent()) {
            ObjectMapperService mapperService = new ObjectMapperService();
            return mapperService.parseJson(ifCondtionExpectedResults.get().toString(), ResultConstant[].class);
        }
        return null;
    }

    default String mapElement(TestStepRecorderRequest testStepRecorderRequest) {
        if(testStepRecorderRequest.getUiIdentifierRequest() != null) {
            return testStepRecorderRequest.getUiIdentifierRequest().getName();
        } else if(testStepRecorderRequest.getDataMap() != null) {
            return testStepRecorderRequest.getDataMap().getUiIdentifier();
        }
        return null;
    }

    default TestStepType gettType(TestStepDTO dto) {
        TestStepType type = dto.getType();
        if(type == TestStepType.ACTION_TEXT) {
            return TestStepType.NLP_TEXT;
        }
        return type;
    }
}
