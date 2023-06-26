

package com.mita.web.request;

import lombok.Data;

import java.util.List;

@Data
public class TestSuiteRequest {
  private Long id;
  private String name;
  private String description;
  private Long workspaceVersionId;
  private Long preRequisite;
  private List<String> tags;
  private List<Long> testCaseIds;
}
