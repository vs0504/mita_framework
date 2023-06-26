package com.mita.automator.suggestion.actions.web;

import com.mita.automator.suggestion.actions.SuggestionAction;
import com.mita.automator.suggestion.actions.SuggestionActionResult;
import org.openqa.selenium.By;

public class GetElementAction extends SuggestionAction {

  @Override
  public void execute() throws Exception {
    setPreviousResult(getDriver().findElement(By.xpath(testCaseStepEntity.getLocatorValue())));
    this.suggestionActionResult = SuggestionActionResult.Success;

  }
}
