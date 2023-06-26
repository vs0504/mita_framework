

package com.mita.automator.actions.mobile;

import com.mita.automator.constants.NaturalTextActionConstants;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MobileNativeInstallAppSnippet extends MobileElementAction {

  private static final String SUCCESS_MESSAGE = "App Installed successfully";

  @Override
  public void execute() throws Exception {
    String downloadURL = getTestDataPropertiesEntity(NaturalTextActionConstants.TEST_STEP_DATA_MAP_KEY_TEST_DATA).getTestDataValuePreSignedURL();
    downloadURL = (downloadURL == null) ? getTestData() : downloadURL;
    getDriver().installApp(downloadURL);
    setSuccessMessage(SUCCESS_MESSAGE);
  }
}
