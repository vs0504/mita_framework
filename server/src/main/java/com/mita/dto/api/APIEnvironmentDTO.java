package com.mita.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mita.serializer.JSONObjectSerializer;
import lombok.Data;
import org.json.JSONObject;

import java.sql.Timestamp;

@Data
public class APIEnvironmentDTO {
  private Long id;
  private String name;
  private String description;
  @JsonProperty("updated_date")
  private Timestamp updatedDate;
  @JsonProperty("created_date")
  private Timestamp createdDate;
  @JsonSerialize(using = JSONObjectSerializer.class)
  private JSONObject parameters;
}
