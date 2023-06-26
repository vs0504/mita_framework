package com.mita.automator.suggestion.groups;

import com.mita.automator.suggestion.actions.web.*;
import com.mita.automator.suggestion.actions.SuggestionAction;
import com.mita.automator.suggestion.actions.SuggestionActionResult;
import org.openqa.selenium.WebElement;

import java.util.List;

public class IframeVerificationSuggestionGroupAction extends SuggestionAction {
  @Override
  public void execute() throws Exception {
    List<WebElement> listOfFrames = (List<WebElement>) new ListFrameAction().runAction();
    for (WebElement frame : listOfFrames) {
      SwitchToFrameAction snippet = new SwitchToFrameAction();
      setPreviousResult(frame);
      snippet.runAction();
      OverlayExistsAction overAction = new OverlayExistsAction();
      overAction.execute();
      CloseOverlayAction closeAction = new CloseOverlayAction();
      closeAction.execute();
      GetElementAction getElementAction = new GetElementAction();
      getElementAction.execute();
    }
    this.suggestionActionResult = SuggestionActionResult.Success;
  }
}
