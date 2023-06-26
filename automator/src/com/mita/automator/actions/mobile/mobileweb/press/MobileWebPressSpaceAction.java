

package com.mita.automator.actions.mobile.mobileweb.press;

import com.mita.automator.actions.mobile.press.PressSpaceSnippet;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;

@Log4j2
public class MobileWebPressSpaceAction extends PressSpaceSnippet {

  private static final String SUCCESS_MESSAGE = "Pressed space successfully";

  @Override
  public void execute() throws Exception {
    getDriver().getKeyboard().pressKey(Keys.SPACE);
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
