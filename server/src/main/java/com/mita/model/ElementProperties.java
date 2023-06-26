package com.mita.model;


import com.mita.automator.entity.ElementEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class ElementProperties {
  private String elementName;
  private String locatorValue;
  private String locatorStrategyName;
  private FindByType findByType;
  private ElementEntity elementEntity;
  private String actionVariableName;
  private boolean isDynamicLocator;
}
