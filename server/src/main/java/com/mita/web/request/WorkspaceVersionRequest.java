

package com.mita.web.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class WorkspaceVersionRequest {
  private Long id;
  private Long workspaceId;
  private String versionName;
  private String description;
}
