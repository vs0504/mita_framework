package com.mita.automator.actions.web.generic;

import com.mita.automator.actions.ElementAction;

public class GetWindowsCountAction extends ElementAction {
  private static final String SUCCESS_MESSAGE_WITH_DATA = "Total number of windows/tabs::<b>%s</b>";

  @Override
  protected void execute() throws Exception {
    setSuccessMessage(String.format(SUCCESS_MESSAGE_WITH_DATA, getDriver().getWindowHandles().size()));
  }
}
