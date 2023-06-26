
package com.mita.web.request;

import lombok.Data;

import java.util.List;

@Data
public class AddonPluginTestDataFunctionRequest {
  private String fullyQualifiedName;
  private String displayName;
  private String description;
  private Boolean deprecated;
  private List<AddonPluginTestDataFunctionParameterRequest> parameters;
}
