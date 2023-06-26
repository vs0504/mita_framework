package com.mita.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mita.model.TestStepPriority;
import com.mita.model.TestStepType;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StepDetailsMetadataDTO {

  private TestStepType type;
  private String action;
  private TestStepPriority priority;
}
