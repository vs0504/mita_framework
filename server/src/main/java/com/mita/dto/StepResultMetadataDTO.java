package com.mita.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mita.model.RestRequestMetadata;
import com.mita.model.RestResponseMetadata;
import com.mita.model.StepResultForLoopMetadata;
import com.mita.model.TestDataType;
import com.mita.serializer.JSONObjectSerializer;
import lombok.Data;
import org.json.JSONObject;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StepResultMetadataDTO {

  private Long id;
  private String action;
  private TestDataType testDataType;
  private String testDataValue;
  private String attribute;
  @JsonSerialize(using = JSONObjectSerializer.class)
  private JSONObject additionalData;
  private RestResponseMetadata restResult;
  private RestRequestMetadata reqEntity;
  @JsonProperty("for_loop")
  private StepResultForLoopMetadata forLoop;
  private StepDetailsMetadataDTO stepDetails;

}
