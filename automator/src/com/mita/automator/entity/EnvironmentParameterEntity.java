

package com.mita.automator.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mita.automator.deserialize.JSONObjectDeserialize;
import lombok.Data;
import org.json.JSONObject;

@Data
public class EnvironmentParameterEntity {
  private Long id;
  private String name;
  private String description;
  @JsonDeserialize(using = JSONObjectDeserialize.class)
  private JSONObject parameters;
}
