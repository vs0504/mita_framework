

package com.mita.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TestPlanLabType {
  TestsigmaLab, Hybrid, PrivateGrid;

  public boolean isHybrid() {
    return this.equals(Hybrid);
  }
}
