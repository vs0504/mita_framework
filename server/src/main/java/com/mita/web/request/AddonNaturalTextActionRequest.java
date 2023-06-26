

package com.mita.web.request;

import com.mita.model.AddonApplicationType;
import com.mita.model.StepActionType;
import lombok.Data;

import java.util.List;

@Data
public class AddonNaturalTextActionRequest {
  private String fullyQualifiedName;
  private String naturalText;
  private AddonApplicationType workspaceType;
  private String description;
  private Boolean deprecated;
  private List<AddonNaturalTextActionParameterRequest> parameters;
  private StepActionType stepActionType;
}
