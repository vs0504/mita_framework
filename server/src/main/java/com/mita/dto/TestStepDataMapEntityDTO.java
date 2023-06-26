package com.mita.dto;

import com.mita.automator.entity.DefaultDataGeneratorsEntity;
import com.mita.model.TestStepConditionType;
import lombok.Data;

@Data
public class TestStepDataMapEntityDTO {
  private Object ifConditionExpectedResults;
  private TestStepConditionType conditionType;
  private String testData;
  private DefaultDataGeneratorsEntity testDataFunction;
  private String testDataType;
  private String element;
  private String fromElement;
  private String toElement;
  private String attribute;
  private TestStepForLoopEntityDTO forLoop;
}
