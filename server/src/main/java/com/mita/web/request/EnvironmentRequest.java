

package com.mita.web.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mita.serializer.JSONObjectDeserializer;
import lombok.Data;
import org.json.JSONObject;

@Data
public class EnvironmentRequest {
  private Long id;
  private String name;
  private String description;
  @JsonDeserialize(using = JSONObjectDeserializer.class)
  private JSONObject parameters;
}
