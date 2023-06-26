

package com.mita.dto;

import lombok.Data;

@Data
public class TestCaseDataDrivenResultDTO {
  private Long id;
  private Long testCaseId;
  private String testDataName;
  private String testData;
  private Long envRunId;
  private Long testCaseResultId;
  private Long iterationResultId;
  private TestCaseResultDTO iterationResult;
}
