

package com.mita.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class WorkspaceVersionDTO {
  private Long id;
  private Long workspaceId;
  private String versionName;
  private String description;
  private WorkspaceDTO workspace;
  private Timestamp CreatedDate;
  private Timestamp UpdatedDate;
}
