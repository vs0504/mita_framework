package com.mita.service;


import com.mita.constants.TSCapabilityType;
import com.mita.exception.TestsigmaException;
import com.mita.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SafariCapabilities extends Capabilities {

  @Autowired
  PlatformsService platformsService;

  @Override
  public void setTestsigmaLabCapabilities(TestDevice testDevice,
                                          Integrations integrations,
                                          List<WebDriverCapability> capabilities) {

  }

  @Override
  public void setHybridCapabilities(TestDevice testDevice,
                                    Integrations integrations,
                                    List<WebDriverCapability> capabilities,
                                    TestPlanLabType testPlanLabType) throws TestsigmaException {
    capabilities.add(new WebDriverCapability(TSCapabilityType.BROWSER_NAME, TSCapabilityType.BROWSER_NAME_SAFARI));
    PlatformOsVersion platformOsVersion = platformsService.getPlatformOsVersion(testDevice.getPlatformOsVersionId(), testDevice.getTestPlanLabType());
    capabilities.add(new WebDriverCapability(TSCapabilityType.OS_VERSION, Platform.Mac + "" + platformOsVersion.getVersion().substring(0, 5)));
  }

}

