

package com.mita.automator.actions.mobile;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MobileNativeResetAppSnippet extends MobileElementAction {

  private static final String SUCCESS_MESSAGE = "Reset app successfully";

  @Override
  public void execute() throws Exception {
    getDriver().resetApp();
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}