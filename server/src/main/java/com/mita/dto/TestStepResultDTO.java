

package com.mita.dto;

import com.mita.automator.entity.TestDataPropertiesEntity;
import com.mita.model.AddonElementData;
import com.mita.model.AddonTestStepTestData;
import com.mita.model.ResultConstant;
import com.mita.model.TestStepPriority;
import com.mita.automator.entity.ElementPropertiesEntity;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Map;

@Data
public class TestStepResultDTO {
  private Long id;
  private Long envRunId;
  private Long testCaseId;
  private Long stepId;
  private Long stepGroupId;
  private ResultConstant result;
  private Integer errorCode;
  private String message;
  private StepResultMetadataDTO metadata;
  private Timestamp startTime;
  private Timestamp endTime;
  private Long duration;
  private Long testCaseResultId;
  private Long groupResultId;
  private String screenshotName;
  private Long parentResultId;
  @ToString.Exclude
  private String webDriverException;
  private TestStepPriority priority;
  private StepDetailsDTO stepDetails;
  private Map<String, TestDataPropertiesEntity> testDataDetails;
  private Map<String, ElementPropertiesEntity> ElementDetails;
  private String screenShotURL;
  private Integer waitTime;
  private Map<String, AddonTestStepTestData> addonTestData;
  private Map<String, AddonElementData> addonElements;
  private Boolean visualEnabled = false;
  private Timestamp createdDate;
  private Timestamp updatedDate;
  private String addonActionLogs;
}
