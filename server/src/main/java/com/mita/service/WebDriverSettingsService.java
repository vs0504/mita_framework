

package com.mita.service;


import com.mita.exception.MitaException;
import com.mita.dto.WebDriverSettingsDTO;
import com.mita.factory.DriverSettingsServiceFactory;
import com.mita.model.*;
import com.mita.web.request.WebDriverSettingsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class WebDriverSettingsService {
  private final MobileInspectionService mobileInspectionService;
  private final AgentDeviceService agentDeviceService;
  private final WebApplicationContext webApplicationContext;
  private final TestDeviceResultService testDeviceResultService;
  private final TestDeviceService testDeviceService;

  public WebDriverSettingsDTO getWebDriverSettings(WebDriverSettingsRequest webDriverSettingsRequest) throws SQLException,
          MitaException, IOException {
    TestDevice testDevice = new TestDevice();
    WorkspaceType workspaceType = webDriverSettingsRequest.getWorkspaceType();

    MobileInspection mobileInspection = this.mobileInspectionService.find(webDriverSettingsRequest.getMobileSessionId());
    if (mobileInspection.getLabType().isHybrid()) {
      if (mobileInspection.getAgentDeviceId() != null) {
        AgentDevice agentDevice = agentDeviceService.find(mobileInspection.getAgentDeviceId());
      }
    }

    testDevice.setAppPathType(mobileInspection.getApplicationPathType());
    if(mobileInspection.getUploadVersionId()!=null) {
      testDevice.setAppUploadId(mobileInspection.getUploadVersion().getUploadId());
      testDevice.setAppUploadVersionId(mobileInspection.getUploadVersionId());
    }
    else {
      testDevice.setAppActivity(mobileInspection.getAppActivity());
      testDevice.setAppPackage(mobileInspection.getApplicationPackage());
    }
    if(mobileInspection.getBundleId()!=null)
      testDevice.setAppBundleId(mobileInspection.getBundleId());
    testDevice.setUdid(String.valueOf(webDriverSettingsRequest.getUniqueId()));
    testDevice.setDeviceId(mobileInspection.getAgentDeviceId());

    WebDriverSettingsDTO webDriverSettingsDTO = getDriverCapabilities(testDevice, workspaceType,
      webDriverSettingsRequest.getExecutionLabType());

    List<Capability> capabilitiesSettings = mobileInspection.getCapabilities();
    List<WebDriverCapability> desiredCapabilities = new ArrayList<>();
    if (capabilitiesSettings != null) {
      capabilitiesSettings.forEach(data -> {
        desiredCapabilities.add(new WebDriverCapability(data.getName(), data.getValue()));
      });
    }
    webDriverSettingsDTO.getWebDriverCapabilities().addAll(desiredCapabilities);
    return webDriverSettingsDTO;
  }

  public WebDriverSettingsDTO getDriverCapabilities(TestDevice testDevice,
                                                    WorkspaceType workspaceType, TestPlanLabType testPlanLabType)
    throws SQLException, MitaException, IOException {
    DriverSettingsServiceFactory driverSettingsServiceFactory = new DriverSettingsServiceFactory(webApplicationContext);
    DriverSettingsService driverSettingsService = driverSettingsServiceFactory.driverSettingsService(testPlanLabType);
    return driverSettingsService.driverSettings(testDevice, workspaceType, testPlanLabType,
      driverSettingsService.getLabDetails(), webApplicationContext);
  }

  public WebDriverSettingsDTO getCapabilities(long id) throws MitaException, IOException, SQLException {
    TestDeviceResult testDeviceResult = testDeviceResultService.find(id);
    TestDevice testDevice = testDeviceService.find(testDeviceResult.getTestDeviceId());
    WorkspaceType workspaceType = testDeviceResult.getTestDevice()
      .getWorkspaceVersion().getWorkspace().getWorkspaceType();
    TestPlanLabType testPlanLabType = testDeviceResult.getTestDevice()
      .getTestPlanLabType();
    return getDriverCapabilities(testDevice, workspaceType, testPlanLabType);
  }
}
