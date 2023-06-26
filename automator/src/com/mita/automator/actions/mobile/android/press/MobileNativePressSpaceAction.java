

package com.mita.automator.actions.mobile.android.press;

import com.mita.automator.actions.mobile.press.PressSpaceSnippet;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MobileNativePressSpaceAction extends PressSpaceSnippet {

  private static final String SUCCESS_MESSAGE = "Pressed Space successfully";

  @Override
  public void execute() throws Exception {
    ((AndroidDriver) getDriver()).pressKeyCode(62);
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
