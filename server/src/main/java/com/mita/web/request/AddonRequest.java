

package com.mita.web.request;

import com.mita.model.AddonStatus;
import lombok.Data;

import java.util.List;

@Data
public class AddonRequest {
  private String name;
  private String version;
  private String description;
  private String externalUniqueId;
  private String externalInstalledVersionUniqueId;
  private AddonStatus status;
  private List<AddonNaturalTextActionRequest> actions;
  private List<AddonPluginTestDataFunctionRequest> testDataFunctions;
}
