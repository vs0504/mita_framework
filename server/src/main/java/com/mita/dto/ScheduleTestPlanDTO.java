

package com.mita.dto;

import com.mita.model.ScheduleStatus;
import com.mita.model.ScheduleType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ScheduleTestPlanDTO {
  private Long id;
  private Long testPlanId;
  private String name;
  private String comments;
  private ScheduleType scheduleType;
  private String scheduleTime;
  private Timestamp createdDate;
  private Timestamp updatedDate;
  private ScheduleStatus status;

}
