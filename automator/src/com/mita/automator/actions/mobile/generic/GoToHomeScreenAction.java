package com.mita.automator.actions.mobile.generic;

import com.mita.automator.actions.mobile.MobileElementAction;

public abstract class GoToHomeScreenAction extends MobileElementAction {
  private static final String SUCCESS_MESSAGE = "Successfully Navigated to Home.";

  @Override
  public void execute() throws Exception {
    pressHome();
    setSuccessMessage(SUCCESS_MESSAGE);
  }

  protected abstract void pressHome();

}

