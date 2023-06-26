package com.mita.automator.actions.web.generic;

import com.mita.automator.actions.ElementAction;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class PressEscapeAction extends ElementAction {
  @Override
  protected void execute() throws Exception {
    Actions actions = new Actions(getDriver());
    actions.sendKeys(Keys.ESCAPE).build().perform();
    setSuccessMessage("Escape key pressed Successfully ");
  }
}
