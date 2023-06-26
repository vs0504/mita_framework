package com.mita.automator.actions.web.click;

import com.mita.automator.constants.NaturalTextActionConstants;
import com.mita.automator.actions.ActionsAction;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ClickIfDisplayedAction extends ActionsAction {
  private static final String SUCCESS_MESSAGE = "Successfully performed click action.";
  private static final String SUCCESS_MESSAGE_NOT_DISPLAYED = "Element with locator <b>%s:%s</b> is present in the page, but it is not displayed. " +
    "Not performing click action.";
  private static final String SUCCESS_MESSAGE_NOT_PRESENT = "Element with locator <b>%s:%s</b> is not present in the page." +
    "Not performing click action.";


  @Override
  protected void execute() throws Exception {
    try {
      findElement();
    } catch (Exception e) {
      log.info("Element is not present, ignore this error,", e);
      setSuccessMessage(String.format(SUCCESS_MESSAGE_NOT_PRESENT, getFindByType(), getLocatorValue()));
      return;
    }
    if (getElement().isDisplayed()) {
      click(NaturalTextActionConstants.TESTS_TEP_DATA_MAP_KEY_ELEMENT, true);
    } else {
      setSuccessMessage(String.format(SUCCESS_MESSAGE_NOT_DISPLAYED, getFindByType(), getLocatorValue()));
      return;
    }
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
