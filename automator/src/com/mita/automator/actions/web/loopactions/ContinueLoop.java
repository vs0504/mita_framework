package com.mita.automator.actions.web.loopactions;

import com.mita.automator.actions.ElementAction;

public class ContinueLoop extends ElementAction {
  private static final String SUCCESS_MESSAGE = "Executed Successfully. <br>Stopping current iteration/loop execution and Continuing with next iteration.";

  @Override
  protected void execute() throws Exception {
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
