package com.mita.automator.actions.mobile.android.generic;


public class GetScreenOrientationAction extends com.mita.automator.actions.mobile.generic.GetScreenOrientationAction {

  public void getOrientation() {
    setActualValue(getDriver().getOrientation());
  }

}

