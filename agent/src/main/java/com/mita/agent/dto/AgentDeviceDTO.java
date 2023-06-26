

package com.mita.agent.dto;


import com.mita.agent.browsers.AgentBrowser;
import com.mita.agent.constants.MobileOs;
import lombok.Data;

import java.util.List;

@Data
public class AgentDeviceDTO {
  private String name;
  private String uniqueId;
  private String productModel;
  private String apiLevel;
  private String osVersion;
  private MobileOs osName;
  private String abi;
  private Boolean isEmulator;
  private Boolean isOnline;
  private Integer screenWidth;
  private Integer screenHeight;
  private List<AgentBrowser> browserList;
}
