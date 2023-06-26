

package com.mita.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mita.model.HttpRequestMethod;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class RestStepRequest {
  private String Url;
  @JsonProperty(value = "request_method")
  private HttpRequestMethod requestMethod;
  @JsonProperty(value = "request_headers")
  private Map<String, String> requestHeaders;
  private Map<String, Object> payload;
  @JsonProperty(value = "follow_redirects")
  private Boolean followRedirects;
  private Integer authorizationType;
  private String authorizationValue;
}
