package com.mita.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mita.serializer.JSONObjectDeserializer;
import com.mita.serializer.JSONObjectSerializer;
import lombok.Data;
import org.json.JSONObject;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ElementMetaDataRequest {
  private String xPath;
  @JsonSerialize(using = JSONObjectSerializer.class)
  @JsonDeserialize(using = JSONObjectDeserializer.class)
  private JSONObject currentElement;
  @JsonSerialize(using = JSONObjectSerializer.class)
  @JsonDeserialize(using = JSONObjectDeserializer.class)
  private JSONObject testData;

  public String getStringCurrentElement() {
    if (this.currentElement != null) {
      return this.currentElement.toString();
    }
    return null;
  }
}
