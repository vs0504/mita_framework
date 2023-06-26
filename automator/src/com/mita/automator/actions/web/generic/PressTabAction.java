package com.mita.automator.actions.web.generic;

import com.mita.automator.actions.ElementAction;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class PressTabAction extends ElementAction {
  @Override
  protected void execute() throws Exception {
    Actions actions = new Actions(getDriver());
    actions.sendKeys(Keys.TAB).build().perform();
    setSuccessMessage("Tab key pressed Successfully ");
  }
}
