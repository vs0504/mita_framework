

package com.mita.automator.actions.mobile.tap;

import com.google.common.collect.ImmutableMap;
import com.mita.automator.actions.constants.ErrorCodes;
import com.mita.automator.actions.mobile.MobileElementAction;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MobileNativeClickOnSearchKeyboardSnippet extends MobileElementAction {

  private static final String SUCCESS_MESSAGE = "Click on search action performed successfully";

  //UnsupportedOperationException
  @Override
  public void execute() throws Exception {
    getDriver().executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
    setSuccessMessage(SUCCESS_MESSAGE);
  }

  @Override
  protected void handleException(Exception e) {
    super.handleException(e);
    if (e instanceof UnsupportedOperationException) {
      setErrorMessage(String.format("Unable click on search Keyboard. unsupported operation"));
      setErrorCode(ErrorCodes.PRESS_INVALID_OPERATION);
    }
  }
}
