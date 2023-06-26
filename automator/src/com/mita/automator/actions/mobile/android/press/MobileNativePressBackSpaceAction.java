

package com.mita.automator.actions.mobile.android.press;

import com.mita.automator.actions.mobile.MobileElementAction;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MobileNativePressBackSpaceAction extends MobileElementAction {
  private static final String SUCCESS_MESSAGE = "Pressed Back Space successfully";

  @Override
  public void execute() throws Exception {
    ((AndroidDriver) getDriver()).pressKeyCode(67);
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
