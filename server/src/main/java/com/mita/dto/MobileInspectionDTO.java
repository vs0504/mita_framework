package com.mita.dto;

import com.mita.model.MobileInspectionStatus;
import com.mita.model.Platform;
import com.mita.model.TestPlanLabType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@EqualsAndHashCode
public class MobileInspectionDTO {
  private Long id;
  private Platform platform;
  private Long agentDeviceId;
  private MobileInspectionStatus status;
  private TestPlanLabType labType;
  private Long platformDeviceId;
  private String appActivity;
  private Long uploadVersionId;
  private String sessionId;
  private Timestamp startedAt;
  private Timestamp finishedAt;
  private Timestamp lastActiveAt;
  private Timestamp createdDate;
  private Timestamp updatedDate;
}
