package com.mita.automator.actions.web.verify;

import com.mita.automator.actions.ElementAction;
import org.springframework.util.Assert;

public class VerifyElementDisabledAction extends ElementAction {

  private static final String SUCCESS_MESSAGE = "The element corresponding to the locator \"%s:%s\"" +
    " is in Disabled state";
  private static final String FAILURE_MESSAGE = "The element corresponding to the locator <b>\"%s:%s\"</b> is  Enabled ";

  @Override
  public void execute() throws Exception {
    findElement();
    setActualValue(getElement().isEnabled());
    Assert.isTrue(Boolean.FALSE.equals(getActualValue()), String.format(FAILURE_MESSAGE, getFindByType(), getLocatorValue()));
    setSuccessMessage(String.format(SUCCESS_MESSAGE, getFindByType(), getLocatorValue()));
  }
}
