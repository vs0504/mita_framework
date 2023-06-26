package com.mita.automator.entity;

import com.mita.automator.actions.FindByType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class ElementPropertiesEntity {
  private String elementName;
  private String locatorValue;
  private String locatorStrategyName;
  private FindByType findByType;
  private ElementEntity elementEntity;
  private String actionVariableName;
  private boolean isDynamicLocator;
}
