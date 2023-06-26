package com.mita.automator.actions.web.generic;

import com.mita.automator.actions.ElementAction;
import org.openqa.selenium.JavascriptExecutor;

public class DeleteCookiesLocalStorageAction extends ElementAction {
  private static final String SUCCESS_MESSAGE = "Deleted all local storage cookies.";

  @Override
  public void execute() throws Exception {
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript("window.localStorage.clear();");
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}

