package com.mita.automator.actions.web.store;

import com.mita.automator.actions.ElementAction;

public class StoreElementsCountAction extends ElementAction {
  private static final String SUCCESS_MESSAGE = "Successfully saved elements count in a run time variable.<br><b>%s=%s</b>";

  @Override
  protected void execute() throws Exception {
    findElement();
    int runTimeVarValue = getElements().size();
    runtimeDataProvider.storeRuntimeVariable(getTestData(), runTimeVarValue + "");
    resultMetadata.put(getTestData(), runTimeVarValue);
    setSuccessMessage(String.format(SUCCESS_MESSAGE, getTestData(), runTimeVarValue));
  }
}
