

package com.mita.dto.export;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mita.model.OSBrowserType;
import lombok.Data;

@Data
public class AgentBrowserXMLDTO {
  private OSBrowserType name;
  private String version;
  private int arch;
  @JsonProperty("major-version")
  private int majorVersion;
}
