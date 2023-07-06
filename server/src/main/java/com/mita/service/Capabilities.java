package com.mita.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.mita.exception.MitaException;
import com.mita.model.TestPlanLabType;
import com.mita.model.Integrations;
import com.mita.model.TestDevice;
import com.mita.model.WebDriverCapability;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
public abstract class Capabilities {

  public List<WebDriverCapability> getCapabilities(TestDevice testDevice,
                                                   Integrations integrations,
                                                   TestPlanLabType testPlanLabType)
    throws MitaException, IOException {
    List<WebDriverCapability> capabilities = new ArrayList<>();
    setDesiredCapabilities(testDevice, capabilities);
    setPlatformSpecificCapabilities(testDevice, testPlanLabType, integrations, capabilities);
    return capabilities;
  }

  public void setDesiredCapabilities(TestDevice testDevice, List<WebDriverCapability> capabilities)
    throws IOException {
    if (testDevice.getCapabilities() != null) {
      String capabilityStr = testDevice.getCapabilities();
      List<Map<String, Object>> additionalCapabilitiesList =
        new ObjectMapperService().parseJson(capabilityStr, new TypeReference<>() {
        });
      for (Map<String, Object> capability : additionalCapabilitiesList) {
        String name = capability.get("name").toString();
        if (!name.equals(""))
          capabilities.add(new WebDriverCapability(name, capability.get("value")));
      }
    }
  }

  protected void setPlatformSpecificCapabilities(TestDevice testDevice,
                                                 TestPlanLabType testPlanLabType,
                                                 Integrations integrations,
                                                 List<WebDriverCapability> capabilities)
    throws MitaException {
    switch (testPlanLabType) {
      case TestsigmaLab:
      case PrivateGrid:
        setTestsigmaLabCapabilities(testDevice, integrations, capabilities);
        break;
      case Hybrid:
        setHybridCapabilities(testDevice, integrations, capabilities, testPlanLabType);
        break;
      default:
        log.error("Unsupported execution lab type - " + testPlanLabType);
    }
  }

  public abstract void setHybridCapabilities(TestDevice testDevice,
                                             Integrations integrations,
                                             List<WebDriverCapability> capabilities,
                                             TestPlanLabType testPlanLabType)
    throws MitaException;

  public abstract void setTestsigmaLabCapabilities(TestDevice testDevice,
                                                   Integrations integrations,
                                                   List<WebDriverCapability> capabilities) throws MitaException;

}
