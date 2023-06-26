package com.mita.dto;

import com.mita.model.StepActionType;
import com.mita.model.WorkspaceType;
import lombok.Data;

import java.util.List;

@Data
public class AddonNaturalTextActionDTO {
  private Long id;
  private String naturalText;
  private String description;
  private WorkspaceType workspaceType;
  private Boolean deprecated;
  private List<AddonNaturalTextActionParameterDTO> parameters;
  private StepActionType stepActionType;
}
