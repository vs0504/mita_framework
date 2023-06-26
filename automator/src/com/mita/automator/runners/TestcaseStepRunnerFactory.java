package com.mita.automator.runners;

import com.mita.automator.entity.Platform;
import com.mita.automator.entity.TestStepType;
import com.mita.automator.entity.WorkspaceType;


public class TestcaseStepRunnerFactory {

  public TestcaseStepRunner getRunner(WorkspaceType workspaceType,
                                      Platform os, TestStepType stepType) {

    if (workspaceType == WorkspaceType.Rest) {
      return new RestTestcaseStepRunner(workspaceType, os);
    }
    if ((stepType == TestStepType.REST_STEP)) {
      return new RestTestcaseStepRunner(workspaceType, os);
    }
    return new WebTestcaseStepRunner(workspaceType, os);
  }
}
