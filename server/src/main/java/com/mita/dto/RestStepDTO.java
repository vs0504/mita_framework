

package com.mita.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mita.model.HttpRequestMethod;
import com.mita.model.RestStepAuthorizationType;
import com.mita.model.RestStepCompareType;
import com.mita.serializer.JSONObjectSerializer;
import lombok.Data;
import org.json.JSONObject;

@Data
public class RestStepDTO implements Cloneable {
  private Long id;
  private Long stepId;
  private String url;
  private HttpRequestMethod method;
  @JsonSerialize(using = JSONObjectSerializer.class)
  private JSONObject requestHeaders;
  private String payload;
  private String status;
  private RestStepCompareType responseCompareType;
  private RestStepCompareType headerCompareType;
  @JsonSerialize(using = JSONObjectSerializer.class)
  private JSONObject responseHeaders;
  private String response;
  @JsonSerialize(using = JSONObjectSerializer.class)
  private JSONObject headerRuntimeData;
  @JsonSerialize(using = JSONObjectSerializer.class)
  private JSONObject bodyRuntimeData;
  private Boolean followRedirects;
  private RestStepAuthorizationType authorizationType;
  @JsonSerialize(using = JSONObjectSerializer.class)
  private JSONObject authorizationValue;
  private Boolean storeMetadata;
  private String expectedResultType;
  private Boolean isMultipart;

  public RestStepDTO clone() throws CloneNotSupportedException {
    return (RestStepDTO) super.clone();
  }
}
