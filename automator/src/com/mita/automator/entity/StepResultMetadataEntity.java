package com.mita.automator.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mita.automator.deserialize.JSONObjectDeserialize;
import com.mita.automator.deserialize.JSONObjectSerializer;
import com.mita.automator.webservices.WebserviceResponse;
import lombok.Data;
import org.json.JSONObject;

import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StepResultMetadataEntity {

  private Long id;
  private String action;
  private String testDataType;
  private String testDataValue;
  private String attribute;
  private StepDetails stepDetails;
  @JsonSerialize(using = JSONObjectSerializer.class)
  @JsonDeserialize(using = JSONObjectDeserialize.class)
  private JSONObject additionalData;
  private Integer preRequisite;
  private RestfulStepEntity reqEntity;
  private WebserviceResponse restResult;
  private StepResultForLoopMetadataEntity forLoop;

  private String log;
  private Object output;
  private Map<String, String> runtimeData;
  private Map<Object, Object> functionStepResultMetadata;
  private Map<Object, Object> snippetResultMetadata;
  private TestCaseStepEntity testStep;

}
