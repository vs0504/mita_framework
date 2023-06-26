

package com.mita.dto;

import com.mita.model.OSBrowserType;
import lombok.Data;

@Data
public class AgentBrowserDTO {
  private OSBrowserType name;
  private String version;
  private int arch;
  private int majorVersion;
}
