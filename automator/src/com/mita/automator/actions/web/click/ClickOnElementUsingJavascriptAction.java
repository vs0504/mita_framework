package com.mita.automator.actions.web.click;

import com.mita.automator.constants.NaturalTextActionConstants;
import com.mita.automator.actions.ActionsAction;

public class ClickOnElementUsingJavascriptAction extends ActionsAction {
  private static final String SUCCESS_MESSAGE = "Successfully performed click action.";

  @Override
  protected void execute() throws Exception {
    clickJavascript(NaturalTextActionConstants.TESTS_TEP_DATA_MAP_KEY_ELEMENT);
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
