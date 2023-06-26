

package com.mita.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WebDriverCapability {
  private String capabilityName;
  private Object capabilityValue;
}
