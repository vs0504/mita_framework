

package com.mita.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mita.serializer.JSONObjectSerializer;
import lombok.Data;
import org.json.JSONObject;

import java.sql.Timestamp;

@Data
public class EnvironmentDTO {
  private Long id;
  private String name;
  private String description;
  private Timestamp updatedDate;
  private Timestamp createdDate;
  @JsonSerialize(using = JSONObjectSerializer.class)
  private JSONObject parameters;
}
