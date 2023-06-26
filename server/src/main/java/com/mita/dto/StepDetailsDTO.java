

package com.mita.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mita.model.TestStepConditionType;
import com.mita.model.TestStepPriority;
import com.mita.model.TestStepType;
import lombok.Data;


@Data
public class StepDetailsDTO {
  private Long id;
  private String stepDescription;
  private TestStepPriority priority;
  private Integer position;
  private Long preRequisiteStepId;
  private String action;
  private Long testCaseId;
  private Long stepGroupId;
  private TestStepDataMapEntityDTO dataMap;
  private String addonNaturalTextActionData;
  private Long addonActionId;
  private String exceptedResult;
  @JsonProperty("natural_text_action_id")
  private Integer naturalTextActionId;
  private TestStepType type;
  private Integer waitTime;
  private TestStepConditionType conditionType;
  private Long parentId;
  private String testDataName;
  private String testDataValue;
  private Boolean ignoreStepResult;
  private Long maxIterations;
}
