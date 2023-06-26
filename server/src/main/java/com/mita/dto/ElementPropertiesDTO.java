package com.mita.dto;

import com.mita.model.FindByType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class ElementPropertiesDTO {
  private String elementName;
  private String locatorValue;
  private String locatorStrategyName;
  private FindByType findByType;
  private ElementDTO elementEntity;
  private String actionVariablename;

}
