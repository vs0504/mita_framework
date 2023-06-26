package com.mita.automator.suggestion.actions.web;

import com.mita.automator.suggestion.actions.SuggestionAction;
import com.mita.automator.suggestion.actions.SuggestionActionResult;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckIfAlertIsAbsentAction extends SuggestionAction {

  @Override
  protected void execute() throws Exception {
    try {
      getWebDriverWait().until(ExpectedConditions.alertIsPresent());
      this.suggestionActionResult = SuggestionActionResult.Failure;
    } catch (Exception exception) {
      this.suggestionActionResult = SuggestionActionResult.Success;
    }
  }

}
