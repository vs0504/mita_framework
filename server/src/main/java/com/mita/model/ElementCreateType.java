

package com.mita.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ElementCreateType {
  MANUAL("Manual"),
  CHROME("Chrome"),
  ADVANCED("Advanced"),
  MOBILE_INSPECTOR("Mobile Inspector");

  private final String displayName;
}

