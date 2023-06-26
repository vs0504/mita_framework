package com.mita.automator.actions.web.scroll;

import com.mita.automator.constants.NaturalTextActionConstants;
import com.mita.automator.actions.ActionsAction;

public class ScrollToElementAction extends ActionsAction {
  @Override
  protected void execute() throws Exception {
    scrollToElement(NaturalTextActionConstants.TESTS_TEP_DATA_MAP_KEY_ELEMENT);
    setSuccessMessage("Successfully scrolled to element");
  }
}
