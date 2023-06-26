

package com.mita.dto;

import com.mita.model.BackupStatus;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class BackupDetailDTO {
  private Long id;
  private String name;
  private String message;
  private BackupStatus status;
  private Timestamp createdDate;
  private Timestamp updatedDate;
  private WorkspaceVersionDTO workspaceVersion;
  private String affectedCasesListPath;
}
