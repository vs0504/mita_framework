

package com.mita.web.request;

import com.mita.model.AppPathType;
import com.mita.model.Platform;
import com.mita.model.TestPlanLabType;
import com.mita.model.WorkspaceType;
import lombok.Data;

import java.net.URL;

@Data
public class WebDriverSettingsRequest {
  private Long mobileSessionId;
  private TestPlanLabType executionLabType;
  private WorkspaceType workspaceType;
  private Platform platform;
  private String platformVersion;
  private String deviceName;
  private String browserName;
  private String browserVersion;
  private URL webDriverServerUrl;
  private AppPathType applicationPathType;
  private Long applicationUploadedId;
  private String applicationPath;
  private String applicationPackage;
  private String applicationActivity;
  private String uniqueId;
  private Long agentDeviceId;
  private String bundleId;
}
