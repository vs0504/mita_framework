

package com.mita.web.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TestCasePriorityRequest {
  private Long id;
  private String name;
  private String displayName;
  private Long workspaceId;
  private Timestamp createdDate;
  private Timestamp updatedDate;
}
