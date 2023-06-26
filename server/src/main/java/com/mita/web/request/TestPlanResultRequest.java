

package com.mita.web.request;

import com.mita.model.ReRunType;
import com.mita.model.ResultConstant;
import com.mita.model.StatusConstant;
import lombok.Data;

import java.util.Map;

@Data
public class TestPlanResultRequest {
  private Long testPlanId;
  private String buildNo;
  private Map<String, Object> runtimeData;
  private ResultConstant result;
  private StatusConstant status;
  private Boolean isReRun = false;
  private ReRunType reRunType = ReRunType.NONE;
  private Long reRunParentId;
  private Map<Long, Long> uploadVersions;

}
