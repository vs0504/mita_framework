

package com.mita.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mita.model.HttpRequestMethod;
import com.mita.model.RestStepAuthorizationType;
import com.mita.model.RestStepCompareType;
import com.mita.serializer.JSONObjectDeserializer;
import lombok.Data;
import org.json.JSONObject;

@Data
public class RestStepDetailsRequest {
  private Long id;
  private Long stepId;
  private String url;
  private HttpRequestMethod method;
  @JsonDeserialize(using = JSONObjectDeserializer.class)
  private JSONObject requestHeaders;
  private String payload;
  @JsonProperty(value = "status")
  private String expectedResponseStatus;
  private String expectedResultType;
  private RestStepCompareType responseCompareType;
  @JsonDeserialize(using = JSONObjectDeserializer.class)
  private JSONObject responseHeaders;
  private String response;
  @JsonDeserialize(using = JSONObjectDeserializer.class)
  private JSONObject headerRuntimeData;
  @JsonDeserialize(using = JSONObjectDeserializer.class)
  private JSONObject bodyRuntimeData;
  private Boolean followRedirects;
  private RestStepAuthorizationType authorizationType;
  @JsonDeserialize(using = JSONObjectDeserializer.class)
  private JSONObject authorizationValue;
  private Boolean storeMetadata;
}
