package com.mita.automator.suggestion.actions.web;

import com.mita.automator.actions.CustomExpectedConditions;
import com.mita.automator.suggestion.actions.SuggestionAction;
import com.mita.automator.suggestion.actions.SuggestionActionResult;
import org.openqa.selenium.WebElement;
import org.springframework.util.Assert;

public class WaitUntilPageFinishedLoadingAction extends SuggestionAction {
  @Override
  protected void execute() throws Exception {
    getWebDriverWait().until(CustomExpectedConditions.waitForPageLoadUsingJS());
    new GetElementAction().execute();
    Assert.isTrue(((WebElement) getPreviousResult()).isDisplayed());
    this.suggestionActionResult = SuggestionActionResult.Success;
  }
}
