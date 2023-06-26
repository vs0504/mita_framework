package com.mita.automator.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mita.automator.constants.ElementCreateType;
import com.mita.automator.deserialize.JSONObjectDeserialize;
import com.mita.automator.deserialize.JSONObjectSerializer;
import lombok.Data;
import org.json.JSONObject;

@Data
public class ElementEntity {
  private Long id;
  private Long workspaceVersionId;
  private String locatorValue;
  private String name;
  private Integer type;
  private ElementCreateType createdType;
  private LocatorType locatorType;
  private String screenName;
  private Boolean isAdvanced = false;
  @JsonDeserialize(using = JSONObjectDeserialize.class)
  @JsonSerialize(using = JSONObjectSerializer.class)
  private JSONObject metadata;
  private String attributes;
  private Boolean isDynamic = false;


}
