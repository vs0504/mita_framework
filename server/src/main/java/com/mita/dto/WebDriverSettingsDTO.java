

package com.mita.dto;

import com.mita.model.WebDriverCapability;
import lombok.Data;

import java.net.URL;
import java.util.List;

@Data
public class WebDriverSettingsDTO {
  private List<WebDriverCapability> webDriverCapabilities;
  private URL webDriverServerUrl;
}
