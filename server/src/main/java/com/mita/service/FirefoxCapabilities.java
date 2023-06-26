package com.mita.service;

import com.mita.constants.TSCapabilityType;
import com.mita.model.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FirefoxCapabilities extends Capabilities {

  @Override
  public void setTestsigmaLabCapabilities(TestDevice testDevice,
                                          Integrations integrations,
                                          List<WebDriverCapability> capabilities) {
  }


  @Override
  public void setHybridCapabilities(TestDevice testDevice,
                                    Integrations integrations,
                                    List<WebDriverCapability> capabilities,
                                    TestPlanLabType testPlanLabType) {
    capabilities.add(new WebDriverCapability(TSCapabilityType.BROWSER_NAME, BrowserType.FIREFOX));
  }
}
