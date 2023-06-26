package com.mita.automator.actions.web.generic;

import com.mita.automator.actions.ElementAction;

public class ReLoadCurrentPageAction extends ElementAction {
  @Override
  public void execute() throws Exception {
    getDriver().navigate().refresh();
    setSuccessMessage("Successfully performed refresh page.");
  }
}
