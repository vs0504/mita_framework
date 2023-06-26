package com.mita.automator.actions.web.generic;

import com.mita.automator.actions.ElementAction;
import org.openqa.selenium.JavascriptExecutor;

public class ClearDataJavaScriptAction extends ElementAction {
  private static final String SUCCESS_MESSAGE = "Cleared data in given element.";

  @Override
  public void execute() throws Exception {
    findElement();
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript("arguments[0].value='';", getElement());
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
