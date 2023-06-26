package com.mita.web.request;

import lombok.Data;

@Data
public class TestStepWhileLoopRequest {
  private Long testDataId;

  public Long getTestDataId() {
    if (testDataId == null)
      return 0L;
    return testDataId;

  }
}
