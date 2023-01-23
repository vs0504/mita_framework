/*
 * *****************************************************************************
 *  Copyright (C) 2020 Testsigma Technologies Inc.
 *  All rights reserved.
 *  ****************************************************************************
 */

package com.testsigma.dto.export;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.testsigma.annotation.JsonListRootName;
import com.testsigma.service.ObjectMapperService;
import lombok.Data;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonListRootName(name = "test-data-sets")
@JsonRootName(value = "test-data-set")
public class TestDataSetXMLDTO extends BaseXMLDTO {
  @JsonProperty("name")
  private String name;
  @JsonProperty("description")
  private String description;
  @JsonProperty("expected-to-fail")
  private Boolean expectedToFail = false;
  @JacksonXmlElementWrapper(localName = "DataMap")
  @JacksonXmlProperty(localName = "DataEntry")
  private List<Entry> dataMap = new ArrayList();

  private Map<String, Object> data = new HashMap<>();

  @JsonIgnore
  public JSONObject getData() {
    return new JSONObject(data);
  }

  public void setData(JSONObject data) {
    data.keySet().forEach((k) -> {
      this.dataMap.add(new Entry(k, data.optString(k, "")));
    });
   this.data = new ObjectMapperService().parseJson(data.toString(), Map.class);
}
}

