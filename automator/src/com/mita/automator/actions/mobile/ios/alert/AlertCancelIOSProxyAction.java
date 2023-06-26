package com.mita.automator.actions.mobile.ios.alert;

import com.mita.automator.actions.constants.ActionConstants;
import com.mita.automator.actions.mobile.mobileweb.generic.AlertCancelMobileWebProxyAction;
import com.mita.automator.exceptions.AutomatorException;


public class AlertCancelIOSProxyAction extends AlertCancelMobileWebProxyAction {
  @Override
  public void execute() throws Exception {
    String key = getTestData();
    switch (key) {
      case ActionConstants.CANCEL:
        CloseAlertAction cancel = (CloseAlertAction) this.initializeChildSnippet(CloseAlertAction.class);
        cancel.execute();
        this.setSuccessMessage(cancel.getSuccessMessage());
        break;
      case ActionConstants.OK:
       AcceptAlertAction enter = (AcceptAlertAction) this.initializeChildSnippet(AcceptAlertAction.class);
        enter.execute();
        this.setSuccessMessage(enter.getSuccessMessage());
        break;
      default:
        setErrorMessage("Unable to Perform Alert Cancel/Ok Action due to error at test data");
        throw new AutomatorException("Unable to Perform Alert Cancel/Ok Action due to error at test data");
    }
  }


}

