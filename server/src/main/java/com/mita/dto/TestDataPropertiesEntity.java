package com.mita.dto;


import com.mita.automator.entity.DefaultDataGeneratorsEntity;
import lombok.Data;

import java.util.Map;

@Data
public class TestDataPropertiesEntity {
  private String testDataName;
  private String testDataValue;
  private String testDataType;
  private Map<String, Object> testDataFunction;
  private String testDataValuePreSignedURL;
  private DefaultDataGeneratorsEntity defaultDataGeneratorsEntity;
}
