package com.mita.service;

import com.mita.constants.TSCapabilityType;
import com.mita.exception.IntegrationNotFoundException;
import com.mita.exception.MitaException;
import com.mita.dto.WebDriverSettingsDTO;
import com.mita.factory.CapabilitiesFactory;
import com.mita.model.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public abstract class DriverSettingsService {
  @Autowired
  PlatformsService platformsService;

  public abstract URL getRemoteDriverUrl(String url, Integrations integrations)
    throws MalformedURLException;

  public WebDriverSettingsDTO driverSettings(TestDevice testDevice, WorkspaceType workspaceType,
                                             TestPlanLabType testPlanLabType,
                                             Integrations integrations,
                                             WebApplicationContext webApplicationContext)
    throws IOException, MitaException, SQLException {
    WebDriverSettingsDTO webDriverSettings = new WebDriverSettingsDTO();
    workspaceType = testDevice.getWorkspaceVersion().getWorkspace().getWorkspaceType();
    webDriverSettings.setWebDriverCapabilities(getCapabilities(testDevice, workspaceType, testPlanLabType,
            integrations, webApplicationContext));
    setApplicationSpecificCapabilities(testDevice, workspaceType, integrations, webDriverSettings);
    return webDriverSettings;
  }

  public List<WebDriverCapability> getCapabilities(TestDevice testDevice,
                                                   WorkspaceType workspaceType, TestPlanLabType testPlanLabType,
                                                   Integrations integrations,
                                                   WebApplicationContext webApplicationContext)
    throws MitaException, IOException {
    CapabilitiesFactory capabilitiesFactory = new CapabilitiesFactory(webApplicationContext);
    Capabilities capabilities;
    if (testDevice.getBrowser() != null)
      capabilities = capabilitiesFactory.capabilities(workspaceType, Browsers.getBrowser(
        testDevice.getBrowser()));
    else if (testDevice.getPlatformBrowserVersionId() != null) {
      PlatformBrowserVersion platformBrowserVersion = this.platformsService.getPlatformBrowserVersion(testDevice.getPlatformBrowserVersionId(), testPlanLabType);
      capabilities = capabilitiesFactory.capabilities(workspaceType, platformBrowserVersion.getName());
    } else
      capabilities = capabilitiesFactory.capabilities(workspaceType, null);
    return capabilities.getCapabilities(testDevice, integrations, testPlanLabType);
  }

  public void setWebCapabilities(TestDevice testDevice,
                                 Integrations integrations,
                                 WebDriverSettingsDTO webDriverSettings)
    throws MalformedURLException, MitaException {
    List<WebDriverCapability> capabilities = webDriverSettings.getWebDriverCapabilities();
    capabilities = capabilities == null ? new ArrayList<WebDriverCapability>() : capabilities;
    String resolution = this.platformsService.getPlatformScreenResolution(testDevice.getPlatformScreenResolutionId(), testDevice.getTestPlanLabType()).getResolution();
    if (!StringUtils.isBlank(resolution)) {
      capabilities.add(new WebDriverCapability(TSCapabilityType.KEY_RESOLUTION, resolution));
    } else {
      capabilities.add(new WebDriverCapability(TSCapabilityType.KEY_RESOLUTION, TSCapabilityType.DEFAULT_RESOLUTION));
    }
    webDriverSettings.setWebDriverCapabilities(capabilities);
  }

  public void setApplicationSpecificCapabilities(TestDevice testDevice,
                                                 WorkspaceType workspaceType,
                                                 Integrations integrations,
                                                 WebDriverSettingsDTO webDriverSettings)
    throws MitaException, MalformedURLException {
    switch (workspaceType) {
      case WebApplication:
        setWebCapabilities(testDevice, integrations, webDriverSettings);
        break;
      case MobileWeb:
      case IOSWeb:
      case AndroidNative:
      case IOSNative:
        setMobileCapabilities(testDevice, workspaceType, integrations, webDriverSettings);
        break;
    }
  }

  public abstract Integrations getLabDetails() throws IntegrationNotFoundException;

  public abstract void setMobileCapabilities(TestDevice testDevice, WorkspaceType workspaceType,
                                             Integrations integrations,
                                             WebDriverSettingsDTO webDriverSettings)
    throws MitaException, MalformedURLException;
}
