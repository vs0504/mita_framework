

package com.mita.automator.actions.mobile.android;

import com.mita.automator.actions.mobile.MobileElementAction;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class OpenNotificationsAction extends MobileElementAction {

  private static final String SUCCESS_MESSAGE = "Open notifications successfully";

  @Override
  public void execute() throws Exception {
    ((AndroidDriver) getDriver()).openNotifications();
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
