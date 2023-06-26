package com.mita.automator.actions.web.generic;

import com.mita.automator.actions.ElementAction;
import org.openqa.selenium.interactions.Actions;

public class RightClickOnElementAction extends ElementAction {
  private static final String SUCCESS_MESSAGE = "Successfully performed Right-click on given element.";

  @Override
  protected void execute() throws Exception {
    Actions actions = new Actions(getDriver());
    findElement();
    actions.contextClick(getElement()).build().perform();
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
