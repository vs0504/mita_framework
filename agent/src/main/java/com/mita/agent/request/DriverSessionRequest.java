

package com.mita.agent.request;


import com.mita.automator.entity.AppPathType;
import com.mita.automator.entity.ExecutionLabType;
import com.mita.automator.entity.Platform;
import com.mita.automator.entity.WorkspaceType;
import lombok.Data;

import java.net.URL;

@Data
public class DriverSessionRequest {
  private Long mobileSessionId;
  private ExecutionLabType executionLabType;
  private WorkspaceType workspaceType;
  private Platform platform;
  private String uniqueId;
  private URL webDriverServerUrl;
  private String jwtApiKey;
  private String agentUUID;
  private AppPathType applicationPathType;
}
