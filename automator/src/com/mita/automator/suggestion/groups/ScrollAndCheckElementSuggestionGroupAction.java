package com.mita.automator.suggestion.groups;

import com.mita.automator.suggestion.actions.SuggestionAction;
import com.mita.automator.suggestion.actions.SuggestionActionResult;
import com.mita.automator.suggestion.actions.web.ScrollSuggestionAction;

public class ScrollAndCheckElementSuggestionGroupAction extends SuggestionAction {
  @Override
  public void execute() throws Exception {
    new ScrollSuggestionAction().execute();
    this.suggestionActionResult = SuggestionActionResult.Success;
  }
}
