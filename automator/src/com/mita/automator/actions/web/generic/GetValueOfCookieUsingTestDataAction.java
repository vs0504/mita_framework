package com.mita.automator.actions.web.generic;

import com.mita.automator.exceptions.AutomatorException;
import com.mita.automator.actions.ElementAction;

public class GetValueOfCookieUsingTestDataAction extends ElementAction {

  private static final String SUCCESS_MESSAGE = "Successfully fetched the value of cookie <b>\"%s\"</b><br> <b>%s=%s</b>.";
  private static final String FAILURE_MESSAGE = "There is no cookie with given name <b>\"%s\"</b>.<br> Available cookies=\"%s\"";

  @Override
  protected void execute() throws Exception {
    String cookieValue = null;
    if (getDriver().manage().getCookieNamed(getTestData()) != null) {
      cookieValue = getDriver().manage().getCookieNamed(getTestData()).getValue();
    } else {
      throw new AutomatorException(String.format(FAILURE_MESSAGE, getTestData(),
        getDriver().manage().getCookies().toString()));
    }
    setSuccessMessage(String.format(SUCCESS_MESSAGE, getTestData(), getTestData(), cookieValue));
  }
}
