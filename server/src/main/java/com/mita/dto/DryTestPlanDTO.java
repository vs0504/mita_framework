

package com.mita.dto;

import com.mita.model.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class DryTestPlanDTO {
  Long id;
  Long workspaceVersionId;
  String name;
  String description;
  Integer elementTimeOut;
  Integer pageTimeout;
  Long environmentId;
  Screenshot screenshot;
  RecoverAction recoveryAction;
  OnAbortedAction onAbortedAction;
  PreRequisiteAction onSuitePreRequisiteFail;
  PreRequisiteAction onTestcasePreRequisiteFail;
  RecoverAction onStepPreRequisiteFail;
  TestPlanType testPlanType;
  WorkspaceVersionDTO workspaceVersion;
  List<TestDeviceDTO> testDevices;
}
