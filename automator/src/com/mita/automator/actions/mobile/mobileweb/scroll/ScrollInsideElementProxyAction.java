package com.mita.automator.actions.mobile.mobileweb.scroll;

import com.mita.automator.actions.constants.ActionConstants;
import com.mita.automator.exceptions.AutomatorException;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class ScrollInsideElementProxyAction extends com.mita.automator.actions.web.scroll.ScrollInsideElementProxyAction {
  @Override
  public void execute() throws Exception {
    String status = getTestData();
    switch (status) {
      case ActionConstants.TOP:
        ScrollInsideElementToTopAction top = (ScrollInsideElementToTopAction) this.initializeChildSnippet(ScrollInsideElementToTopAction.class);
        top.execute();
        this.setSuccessMessage(top.getSuccessMessage());
        break;
      case ActionConstants.BOTTOM:
        ScrollInsideElementToBottomAction bottom = (ScrollInsideElementToBottomAction) this.initializeChildSnippet(ScrollInsideElementToBottomAction.class);
        bottom.execute();
        this.setSuccessMessage(bottom.getSuccessMessage());
        break;
      default:
        setErrorMessage("Unable to Perform Scroll Action due to error at test data");
        throw new AutomatorException("Unable to Scroll Verify Action due to error at test data");
    }
  }
}
