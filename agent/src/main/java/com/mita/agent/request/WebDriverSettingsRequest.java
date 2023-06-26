

package com.mita.agent.request;

import com.mita.automator.entity.Platform;
import com.mita.automator.entity.WorkspaceType;
import lombok.Data;

@Data
public class WebDriverSettingsRequest {
  private WorkspaceType workspaceType;
  private Platform platform;
  private String platformVersion;
  private String deviceName;
  private String browserName;
  private String browserVersion;
}
