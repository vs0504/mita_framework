

package com.mita.automator.actions.mobile;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MobileNativeCloseSnippet extends MobileElementAction {

  private static final String SUCCESS_MESSAGE = "Closed App successfully";

  @Override
  public void execute() throws Exception {
    getDriver().closeApp();
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
