

package com.mita.automator.entity;

import com.mita.automator.drivers.WebDriverCapability;
import lombok.Data;

import java.net.URL;
import java.util.List;

@Data
public class WebDriverSettingsDTO {
  private List<WebDriverCapability> webDriverCapabilities;
  private URL webDriverServerUrl;
}
