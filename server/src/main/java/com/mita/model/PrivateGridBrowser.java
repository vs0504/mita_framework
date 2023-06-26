

package com.mita.model;

import lombok.Data;

@Data
public class PrivateGridBrowser {
  private OSBrowserType browserName;
  private Integer maxInstances;
  private Platform platform;
  private String platformName;
  private String version;
}
