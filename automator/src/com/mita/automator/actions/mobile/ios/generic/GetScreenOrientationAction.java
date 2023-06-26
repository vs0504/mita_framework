package com.mita.automator.actions.mobile.ios.generic;


public class GetScreenOrientationAction extends com.mita.automator.actions.mobile.generic.GetScreenOrientationAction {

  public void getOrientation() {
    setActualValue(getDriver().getOrientation());
  }

}

