

package com.mita.automator.actions.mobile.android.verify;

import com.mita.automator.constants.NaturalTextActionConstants;
import com.mita.automator.actions.mobile.MobileElementAction;
import io.appium.java_client.MobileElement;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.Assert;

@Log4j2
public class MobileNativeVerifyAlertPresentWithTextAction extends MobileElementAction {

  private static final String SUCCESS_MESSAGE = "Alert is displayed successfully";
  private static final String FAILURE_MESSAGE = "Unable to find an Alert which contains given text <b>\"%s\"</b>, " +
    "please verify if the alert is present.";
  private static final String FAILURE_MESSAGE_NULL = "Could not find an alert on current page.";
  private static final String PARAMETERIZED_XPATH = "//android.widget.TextView[@text='$PARAMETER']";

  @Override
  public void execute() throws Exception {
    verifyAlertPresence(FAILURE_MESSAGE_NULL);
    constructElementWithDynamicXpath(PARAMETERIZED_XPATH, NaturalTextActionConstants.TESTS_TEP_DATA_MAP_KEY_ELEMENT, NaturalTextActionConstants.TEST_STEP_DATA_MAP_KEY_TEST_DATA, null, false);
    findElement();
    MobileElement targetElement = (MobileElement) getElement();
    Assert.isTrue(targetElement.isDisplayed(),
      String.format("The Alert with text %s is not displayed in this page", getTestData()));
    setSuccessMessage(SUCCESS_MESSAGE);
  }

  @Override
  protected void handleException(Exception e) {
    super.handleException(e);
    updateErrorMessageForDynamicLocatorTypes(e, String.format(FAILURE_MESSAGE, getTestData()));
  }
}
