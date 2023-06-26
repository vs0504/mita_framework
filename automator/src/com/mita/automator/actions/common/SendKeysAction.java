

package com.mita.automator.actions.common;

import com.mita.automator.actions.ElementAction;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SendKeysAction extends ElementAction {

  private static final String SUCCESS_MESSAGE = "Successfully entered data \"%s\"";

  @Override
  public void execute() throws Exception {
    findElement();
    getElement().sendKeys(getTestData());
    setSuccessMessage(String.format(SUCCESS_MESSAGE, getTestData()));
  }
}
