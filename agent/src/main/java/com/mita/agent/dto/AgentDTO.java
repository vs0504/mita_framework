

package com.mita.agent.dto;

import com.mita.agent.browsers.AgentBrowser;
import com.mita.agent.constants.AgentOs;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class AgentDTO {
  private Long id;
  private String uniqueId;
  @ToString.Exclude
  private String jwtApiKey;
  private String agentVersion;
  private String hostName;
  private String osVersion;
  private Boolean isRegistered;
  private List<AgentBrowser> browserList;
  private String agentBuild;
  private String ipAddress;
  private String title;
  private AgentOs osType;
}
