

package com.mita.web.request;

import com.mita.model.OSBrowserType;
import com.mita.model.Platform;
import lombok.Data;

@Data
public class PrivateGridBrowserRequest {
  private OSBrowserType browserName;
  private Integer maxInstances;
  private Platform platform;
  private String platformName;
  private String version;
}
