package com.mita.automator.suggestion.groups;

import com.mita.automator.suggestion.actions.SuggestionAction;
import com.mita.automator.suggestion.actions.SuggestionActionResult;
import com.mita.automator.suggestion.actions.web.CloseOverlayAction;
import com.mita.automator.suggestion.actions.web.OverlayExistsAction;

public class OverlayExistsSuggestionGroupAction extends SuggestionAction {
  @Override
  public void execute() throws Exception {
    new OverlayExistsAction().run();
    new CloseOverlayAction().run();
    this.suggestionActionResult = SuggestionActionResult.Success;
  }
}
