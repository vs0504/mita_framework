

package com.mita.dto;

import com.mita.model.StepActionType;
import com.mita.model.WorkspaceType;
import lombok.Data;

import java.util.List;

@Data
public class NaturalTextActionsDTO {
  private Long id;
  private WorkspaceType workspaceType;
  private String naturalText;
  private NaturalTextActionDataDTO data;
  private String displayName;
  private String action;
  private List allowedValues;
  private StepActionType stepActionType;
}
