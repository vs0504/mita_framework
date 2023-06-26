

package com.mita.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NaturalTextActionData {
  @JsonProperty("test-data")
  public String testData;
  @JsonProperty("element")
  public String element;
  @JsonProperty("attribute")
  public String attribute;
  @JsonProperty("from-element")
  public String fromElement;
  @JsonProperty("to-element")
  public String toElement;
}
