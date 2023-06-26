

package com.mita.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mita.serializer.JSONObjectSerializer;
import lombok.Data;
import org.json.JSONObject;

@Data
public class TestDataSetDTO {
  private Long id;
  private Long testDataProfileId;
  private String name;
  private Long testDataId;
  private String description;
  private Boolean expectedToFail;
  private Long position;
  @JsonSerialize(using = JSONObjectSerializer.class)
  private JSONObject data;
}
