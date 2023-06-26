package com.mita.automator.actions.mobile.android.press;

import com.mita.automator.actions.constants.ActionConstants;
import com.mita.automator.actions.mobile.mobileweb.press.PressMobileWebKeyProxyAction;
import com.mita.automator.exceptions.AutomatorException;
import com.mita.automator.actions.mobile.android.tap.TapOnSearchInKeyboardAction;


public class PressMobileNativeKeyProxyAction extends PressMobileWebKeyProxyAction {
  @Override
  public void execute() throws Exception {
    String key = getTestData();
    switch (key) {
      case ActionConstants.SPACE:
        MobileNativePressSpaceAction space = (MobileNativePressSpaceAction) this.initializeChildSnippet(MobileNativePressSpaceAction.class);
        space.execute();
        this.setSuccessMessage(space.getSuccessMessage());
        break;
      case ActionConstants.ENTER:
        MobileNativePressEnterAction enter = (MobileNativePressEnterAction) this.initializeChildSnippet(MobileNativePressEnterAction.class);
        enter.execute();
        this.setSuccessMessage(enter.getSuccessMessage());
        break;
      case ActionConstants.BACKSPACE:
        MobileNativePressBackSpaceAction backSpace = (MobileNativePressBackSpaceAction) this.initializeChildSnippet(MobileNativePressBackSpaceAction.class);
        backSpace.execute();
        this.setSuccessMessage(backSpace.getSuccessMessage());
        break;
      case ActionConstants.SEARCH:
        TapOnSearchInKeyboardAction available = (TapOnSearchInKeyboardAction) this.initializeChildSnippet(TapOnSearchInKeyboardAction.class);
        available.execute();
        this.setSuccessMessage(available.getSuccessMessage());
        break;
      default:
        setErrorMessage("Unable to Perform Press Key Action due to error at test data");
        throw new AutomatorException("Unable to Perform Press Key Action due to error at test data");
    }
  }


}

