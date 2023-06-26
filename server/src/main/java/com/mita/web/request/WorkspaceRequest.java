

package com.mita.web.request;

import com.mita.model.WorkspaceType;
import lombok.Data;

import java.util.List;

@Data
public class WorkspaceRequest {
  private Long id;
  private String name;
  private String description;
  private WorkspaceType workspaceType;
  private List<WorkspaceVersionRequest> workspaceVersions;
}
