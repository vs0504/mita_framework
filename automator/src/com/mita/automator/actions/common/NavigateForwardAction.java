package com.mita.automator.actions.common;

import com.mita.automator.actions.DriverAction;

public class NavigateForwardAction extends DriverAction {
  private static final String SUCCESS_MESSAGE = "Navigate forward executed successfully";

  @Override
  public void execute() throws Exception {
    getDriver().navigate().forward();
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
