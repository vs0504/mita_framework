package com.mita.os.stats.entity;

import com.mita.model.TestPlanLabType;
import com.mita.model.WorkspaceType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TestPlanRunStatEntity extends BaseStatEntity {
  private Long testPlanRunId;
  private String testPlanType;
  private WorkspaceType applicationType;
  private TestPlanLabType testPlanLabType;
}
