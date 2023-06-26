

package com.mita.automator.actions.mobile.android.press;

import com.mita.automator.actions.mobile.MobileElementAction;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MobileNativePressEnterAction extends MobileElementAction {
  private static final String SUCCESS_MESSAGE = "Pressed Enter key successfully";

  @Override
  public void execute() throws Exception {
    ((AndroidDriver) getDriver()).pressKeyCode(66);
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
