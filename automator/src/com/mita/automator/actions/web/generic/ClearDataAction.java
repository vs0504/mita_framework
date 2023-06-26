package com.mita.automator.actions.web.generic;

import com.mita.automator.actions.ElementAction;

public class ClearDataAction extends ElementAction {
  private static final String SUCCESS_MESSAGE = "Cleared data in given element.";

  @Override
  public void execute() throws Exception {
    findElement();
    getElement().clear();
    setSuccessMessage(SUCCESS_MESSAGE);
  }

}
