package com.mita.automator.suggestion.actions.web;

import com.mita.automator.suggestion.actions.SuggestionAction;
import com.mita.automator.suggestion.actions.SuggestionActionResult;
import org.openqa.selenium.WebElement;
import org.springframework.util.Assert;

public class CheckElementIsNotSelectAction extends SuggestionAction {
  @Override
  protected void execute() throws Exception {
    new GetElementAction().execute();
    Assert.isTrue(!((WebElement) getPreviousResult()).getTagName().equalsIgnoreCase("input"));
    this.suggestionActionResult = SuggestionActionResult.Success;
  }
}
