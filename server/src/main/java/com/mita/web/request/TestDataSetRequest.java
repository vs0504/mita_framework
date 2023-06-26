

package com.mita.web.request;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mita.serializer.JSONObjectSerializer;
import lombok.Data;
import org.json.JSONObject;

@Data
public class TestDataSetRequest {
  private Long id;
  private Long testDataProfileId;
  private String name;
  private String description;
  private Boolean expectedToFail;
  @JsonAlias("data")
  //@JsonDeserialize(using = JSONTestdataObjectDeserializer.class)
  @JsonSerialize(using = JSONObjectSerializer.class)
  private JSONObject data;
}
