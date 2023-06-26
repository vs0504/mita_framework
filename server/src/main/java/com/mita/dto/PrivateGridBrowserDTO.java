

package com.mita.dto;

import com.mita.model.OSBrowserType;
import com.mita.model.Platform;
import lombok.Data;

@Data
public class PrivateGridBrowserDTO {
  private OSBrowserType browserName;
  private Integer maxInstances;
  private Platform platform;
  private String platformName;
  private String version;
}
