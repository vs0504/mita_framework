package com.mita.automator.suggestion.actions.web;

import com.mita.automator.suggestion.actions.SuggestionAction;
import com.mita.automator.suggestion.actions.SuggestionActionResult;

public class GetWindowCountAction extends SuggestionAction {
  @Override
  protected void execute() throws Exception {
    engineResult.getMetaData().setTabCount(new Integer(getDriver().getWindowHandles().size()).toString());
    this.suggestionActionResult = SuggestionActionResult.Success;
  }
}
