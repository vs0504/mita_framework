package com.mita.automator.actions.web.loopactions;

import com.mita.automator.actions.ElementAction;

public class BreakLoop extends ElementAction {
  private static final String SUCCESS_MESSAGE = "Executed Successfully. Further iterations will not be executed.<br>" +
    "If it is a step group, group execution will be stopped.";

  @Override
  protected void execute() throws Exception {
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
