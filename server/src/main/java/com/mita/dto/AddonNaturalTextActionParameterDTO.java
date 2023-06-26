package com.mita.dto;

import com.mita.model.AddonActionParameterType;
import lombok.Data;

import java.util.List;

@Data
public class AddonNaturalTextActionParameterDTO {
  private Long id;
  private String name;
  private String reference;
  private String description;
  private AddonActionParameterType type;
  private List allowedValues;
}
