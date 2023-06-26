

package com.mita.web.request;

import com.mita.model.OSBrowserType;
import lombok.Data;

@Data
public class AgentBrowserRequest {
  private OSBrowserType name;
  private String version;
  private int arch;
  private int majorVersion;
}
