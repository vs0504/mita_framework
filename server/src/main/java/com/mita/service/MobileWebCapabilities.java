package com.mita.service;


import com.mita.constants.TSCapabilityType;
import com.mita.model.Integrations;
import com.mita.model.TestDevice;
import com.mita.model.TestPlanLabType;
import com.mita.model.WebDriverCapability;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MobileWebCapabilities extends Capabilities {

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
    capabilities.add(new WebDriverCapability(TSCapabilityType.BROWSER, testDevice.getBrowser()));
    capabilities.add(new WebDriverCapability(TSCapabilityType.BROWSER_NAME, testDevice.getBrowser()));
  }
}
