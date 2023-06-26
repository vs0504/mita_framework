

package com.mita.automator.actions.common;

import com.mita.automator.actions.constants.ErrorCodes;
import com.mita.automator.formatters.NumberFormatter;
import com.mita.automator.actions.ElementAction;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;


@Log4j2
public class WaitAction extends ElementAction {

  private static final String SUCCESS_MESSAGE = "Waited for %s seconds successfully";
  private static final String FAILURE_NOT_A_NUMBER = "Please provide a valid number in test data. Given test data <b>%s</b> is not a number.";

  @Override
  public void execute() throws Exception {
    Integer waitInSeconds = NumberFormatter.getIntegerValue(getTestData(), String.format(FAILURE_NOT_A_NUMBER, getTestData()));
    Long timeWithWait = System.currentTimeMillis() + (waitInSeconds * 1000);
    Long sleepStartTime = System.currentTimeMillis();
    while (true) {
      Long curTime = System.currentTimeMillis();
      if (curTime >= timeWithWait) {
        log.info(String.format("Wait time completed,exiting wait"));
        break;
      } else if (curTime >= (sleepStartTime + (50 * 1000))) {
        try {
          getDriver().findElement(By.xpath("error"));
        } catch (Exception ex) {
          sleepStartTime = (sleepStartTime + (50 * 1000));
        }
      }
    }
    setSuccessMessage(String.format(SUCCESS_MESSAGE, getTestData()));
  }

  @Override
  protected void handleException(Exception e) {
    super.handleException(e);
    if (e instanceof NumberFormatException) {
      log.error("Cannot convert testdata to integer,", e);
      setErrorMessage("Please provide a valid number,Ex: Wait for 5 seconds");
      setErrorCode(ErrorCodes.GENERIC_ERROR);
    }
  }
}
