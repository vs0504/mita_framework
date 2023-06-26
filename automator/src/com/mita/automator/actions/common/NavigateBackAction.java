

package com.mita.automator.actions.common;

import com.mita.automator.actions.DriverAction;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class NavigateBackAction extends DriverAction {
  private static final String SUCCESS_MESSAGE = "Navigate back executed successfully";

  @Override
  public void execute() throws Exception {
    getDriver().navigate().back();
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}