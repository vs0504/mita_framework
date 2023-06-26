package com.mita.automator.actions.web.click;

import com.mita.automator.constants.NaturalTextActionConstants;
import com.mita.automator.actions.ActionsAction;

public class ClickOnElementAction extends ActionsAction {
  private static final String SUCCESS_MESSAGE = "Successfully performed click action.";

  @Override
  protected void execute() throws Exception {
    click(NaturalTextActionConstants.TESTS_TEP_DATA_MAP_KEY_ELEMENT, true);
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
