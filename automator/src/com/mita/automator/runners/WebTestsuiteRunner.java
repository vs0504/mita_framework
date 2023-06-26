package com.mita.automator.runners;

import com.mita.automator.constants.DriverSessionType;
import com.mita.automator.exceptions.AutomatorException;
import com.mita.automator.drivers.DriverManager;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class WebTestsuiteRunner extends TestsuiteRunner {

  public WebTestsuiteRunner() {
    super();
  }

  public void startSession(Long entityId, DriverSessionType driverSessionType) throws AutomatorException {
    DriverManager.getDriverManager(testDeviceEntity, getWorkspaceType(), testDeviceSettings.getOs(),
      entityId, driverSessionType);
  }
}
