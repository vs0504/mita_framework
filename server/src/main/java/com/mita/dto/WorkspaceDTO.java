

package com.mita.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mita.model.WorkspaceType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class WorkspaceDTO {
  private Long id;
  private String name;
  private String description;
  @JsonProperty("is_demo")
  private Boolean isDemo;
  private WorkspaceType workspaceType;
  private Timestamp createdDate;
  private Timestamp updatedDate;
}
