package com.mita.os.stats.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TestPlanStatEntity extends BaseStatEntity {
  private Long testPlanId;
  private String entityType;
}
