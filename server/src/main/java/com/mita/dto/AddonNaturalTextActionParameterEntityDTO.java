package com.mita.dto;

import com.mita.model.AddonActionParameterType;
import lombok.Data;

@Data
public class AddonNaturalTextActionParameterEntityDTO {

  private Long id;
  private String name;
  private String description;
  private AddonActionParameterType type;
  private Long pluginActionId;
  private String reference;
}
