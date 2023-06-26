

package com.mita.model;

import lombok.Data;

@Data
public class AgentBrowser {
  private OSBrowserType name;
  private String version;
  private int arch;
  private int majorVersion;
}
