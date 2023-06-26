

package com.mita.automator.actions.mobile.android.verify;

import com.mita.automator.actions.mobile.MobileElementAction;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.springframework.util.Assert;

@Log4j2
public class MobileNativeVerifyDisableSwitchAction extends MobileElementAction {

  private static final String SUCCESS_MESSAGE = "Verified switch enabled successfully";

  @Override
  public void execute() throws Exception {
    findElement();
    WebElement targetElement = getElement();
    Assert.isTrue(!targetElement.isSelected(),
      String.format("The element corresponding to the locator type %s:%s is in checked state",
        getElementSearchCriteria().getFindByType(), getElementSearchCriteria().getByValue()));
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
