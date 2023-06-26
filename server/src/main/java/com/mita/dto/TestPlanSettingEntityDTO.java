

package com.mita.dto;

import com.mita.model.OnAbortedAction;
import com.mita.model.PreRequisiteAction;
import com.mita.model.RecoverAction;
import com.mita.model.Screenshot;
import lombok.Data;

@Data
public class TestPlanSettingEntityDTO {
  private Integer elementTimeOut;
  private Integer pageTimeOut;
  private Screenshot screenshot;
  private RecoverAction recoveryAction;
  private OnAbortedAction onAbortedAction;
  private PreRequisiteAction onSuitePreRequisiteFail;
  private PreRequisiteAction onTestcasePreRequisiteFail;
  private RecoverAction onStepPreRequisiteFail;
  private Boolean hasSuggestionFeature = false;
  private Boolean retrySessionCreation = false;
  private Integer retrySessionCreationTimeout;
}
