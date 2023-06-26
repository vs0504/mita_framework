

package com.mita.automator.drivers.mobile;

import com.mita.automator.constants.TSCapabilityType;
import com.mita.automator.entity.AppPathType;
import com.mita.automator.entity.Platform;
import com.mita.automator.exceptions.AutomatorException;
import com.mita.automator.drivers.WebDriverCapability;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

@Log4j2
public class IosDriver extends MobileDriver {

  public IosDriver() {
    super();
  }


  @Override
  protected void setCommonCapabilities() throws AutomatorException {
    super.setCommonCapabilities();
    capabilities.add(new WebDriverCapability(MobileCapabilityType.PLATFORM_NAME, Platform.iOS.name()));
  }

  @Override
  protected void setHybridCapabilities() throws AutomatorException, MalformedURLException {
    super.setHybridCapabilities();
    AppPathType appPathType = settings.getAppPathType();
    if (appPathType == AppPathType.APP_DETAILS) {
      capabilities.add(new WebDriverCapability(TSCapabilityType.BUNDLE_ID, settings.getBundleId()));
    }
  }

  @Override
  protected void createDriverInstance(DesiredCapabilities desiredCapabilities) throws AutomatorException {
    remoteWebDriver = new IOSDriver<>(getRemoteServerURL(), desiredCapabilities);
  }
}
