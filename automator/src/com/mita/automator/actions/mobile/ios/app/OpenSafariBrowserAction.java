package com.mita.automator.actions.mobile.ios.app;

import com.mita.automator.actions.mobile.MobileElementAction;

public class OpenSafariBrowserAction extends MobileElementAction {

  private final static String SUCCESS_MESSAGE = "Safari Browser opened successfully.";

  @Override
  protected void execute() throws Exception {
    getDriver().activateApp("com.apple.mobilesafari");
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
