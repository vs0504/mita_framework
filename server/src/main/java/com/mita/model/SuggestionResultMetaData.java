package com.mita.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mita.serializer.JSONObjectDeserializer;
import com.mita.serializer.JSONObjectSerializer;
import lombok.Data;
import org.json.JSONObject;


@Data
public class SuggestionResultMetaData {
  private String tagName;
  private String tabCount;
  @JsonSerialize(using = JSONObjectSerializer.class)
  @JsonDeserialize(using = JSONObjectDeserializer.class)
  private JSONObject suggestions;
}
