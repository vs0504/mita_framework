

package com.mita.automator.actions.mobile.press;

import com.mita.automator.actions.mobile.MobileElementAction;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.Keys;

@Log4j2
public class PressBackSpaceSnippet extends MobileElementAction {

  private static final String SUCCESS_MESSAGE = "Pressed backspace key successfully";
  private static final String FAILURE_MESSAGE = "Unable to tap on BACK SPACE key due to unavailability of keyboard. please ensure keyboard is opened.";

  @Override
  public void execute() throws Exception {
    getDriver().getKeyboard().pressKey(Keys.BACK_SPACE);
    setSuccessMessage(SUCCESS_MESSAGE);
  }

  @Override
  protected void handleException(Exception e) {
    super.handleException(e);
    if (e instanceof InvalidElementStateException) {
      setErrorMessage(FAILURE_MESSAGE);
    }
  }
}
