

package com.mita.web.request;

import com.mita.model.AddonActionParameterType;
import lombok.Data;

import java.util.List;

@Data
public class AddonNaturalTextActionParameterRequest {
  private String name;
  private String reference;
  private String description;
  private AddonActionParameterType type;
  private List allowedValues;
}
