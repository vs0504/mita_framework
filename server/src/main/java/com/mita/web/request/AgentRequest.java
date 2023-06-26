

package com.mita.web.request;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mita.model.AgentBrowser;
import com.mita.model.AgentOs;
import com.mita.service.ObjectMapperService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Data
@Log4j2
public class AgentRequest {
  private Long id;
  private String agentVersion;
  private String hostName;
  private String title;
  private String ipAddress;
  private AgentOs osType;
  private String osVersion;
  private List<AgentBrowserRequest> browserList;

  public List<AgentBrowser> getAgentBrowserList() {
    return browserList == null ? new ArrayList<>() :
      new ObjectMapperService().parseJson(new ObjectMapperService().convertToJson(browserList), new TypeReference<>() {
      });
  }
}
