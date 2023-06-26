

package com.mita.automator.drivers;

import lombok.Data;

@Data
public class WebDriverCapability {

  private String capabilityName;
  private Object capabilityValue;

  public WebDriverCapability() {

  }

  public WebDriverCapability(String capabilityName, Object capabilityValue) {
    this.capabilityName = capabilityName;
    this.capabilityValue = capabilityValue;
  }
}
