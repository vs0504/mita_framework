

package com.mita.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TestCaseTypeDTO {
  private Long id;
  private String name;
  private String displayName;
  @JsonProperty("workspace_id")
  private Long workspaceId;
  private Timestamp createdDate;
  private Timestamp updatedDate;
}
