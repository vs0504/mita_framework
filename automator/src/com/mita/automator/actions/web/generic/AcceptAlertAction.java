package com.mita.automator.actions.web.generic;

import com.mita.automator.actions.constants.ErrorCodes;
import com.mita.automator.actions.ElementAction;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.util.Assert;

public class AcceptAlertAction extends ElementAction {
  private static final String SUCCESS_MESSAGE = "Alert accepted";
  private static final String FAILURE_MESSAGE = "Could not find any alert on the current page";

  @Override
  public void execute() throws Exception {
    Alert alert = getWebDriverWait().until(ExpectedConditions.alertIsPresent());
    Assert.notNull(alert, FAILURE_MESSAGE);
    alert.accept();
    setSuccessMessage(SUCCESS_MESSAGE);
  }

  @Override
  protected void handleException(Exception e) {
    super.handleException(e);
    if (e instanceof TimeoutException) {
      setErrorMessage("Unable to accept Alert. If alert is yet to load, please try increasing test wait time.");
      setErrorCode(ErrorCodes.NO_ALERT_PRESENT_EXCEPTION);
    }
  }
}