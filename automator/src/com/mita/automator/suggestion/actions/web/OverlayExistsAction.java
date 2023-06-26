package com.mita.automator.suggestion.actions.web;

import com.mita.automator.suggestion.actions.SuggestionAction;
import com.mita.automator.suggestion.actions.SuggestionActionResult;
import com.mita.automator.exceptions.AutomatorException;

public class OverlayExistsAction extends SuggestionAction {

  @Override
  public void execute() throws AutomatorException {
    getDriver().switchTo().alert();
    this.suggestionActionResult = SuggestionActionResult.Success;
  }
}
