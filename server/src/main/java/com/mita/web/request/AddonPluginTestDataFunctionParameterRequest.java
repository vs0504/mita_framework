
package com.mita.web.request;

import com.mita.model.AddonPluginTestDataFunctionParameterType;
import lombok.Data;

@Data
public class AddonPluginTestDataFunctionParameterRequest {
  private String name;
  private String reference;
  private String description;
  private AddonPluginTestDataFunctionParameterType type;
}
