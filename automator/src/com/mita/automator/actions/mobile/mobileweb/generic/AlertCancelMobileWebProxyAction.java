package com.mita.automator.actions.mobile.mobileweb.generic;

import com.mita.automator.actions.constants.ActionConstants;
import com.mita.automator.exceptions.AutomatorException;
import com.mita.automator.actions.mobile.mobileweb.press.PressMobileWebKeyProxyAction;


public class AlertCancelMobileWebProxyAction extends PressMobileWebKeyProxyAction {
  @Override
  public void execute() throws Exception {
    String key = getTestData();
    switch (key) {
      case ActionConstants.CANCEL:
        DismissAlertAction cancel = (DismissAlertAction) this.initializeChildSnippet(DismissAlertAction.class);
        cancel.execute();
        this.setSuccessMessage(cancel.getSuccessMessage());
        break;
      case ActionConstants.OK:
        AcceptAlertAction ok = (AcceptAlertAction) this.initializeChildSnippet(AcceptAlertAction.class);
        ok.execute();
        this.setSuccessMessage(ok.getSuccessMessage());
        break;
      default:
        setErrorMessage("Unable to Perform Alert Cancel/Ok Action due to error at test data");
        throw new AutomatorException("Unable to Perform Alert Cancel/Ok Action due to error at test data");
    }
  }


}

