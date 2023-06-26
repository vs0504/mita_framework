package com.mita.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mita.serializer.JSONObjectDeserializer;
import com.mita.serializer.JSONObjectSerializer;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;

@Data
@Log4j2
@JsonIgnoreProperties(ignoreUnknown = true)
public class ElementMetaData {
  @JsonProperty("xPath")
  private String xPath;
  @JsonProperty("current-element")
  private String currentElement;
  @JsonProperty("testdata")
  @JsonSerialize(using = JSONObjectSerializer.class)
  @JsonDeserialize(using = JSONObjectDeserializer.class)
  private JSONObject testData;
}
