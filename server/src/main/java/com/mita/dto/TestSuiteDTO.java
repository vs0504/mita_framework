

package com.mita.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TestSuiteDTO {
  private Long id;
  private Long workspaceVersionId;
  private Long preRequisite;
  private String name;
  private String description;
  private Timestamp createdDate;
  private Timestamp updatedDate;
  private TestSuiteDTO preRequisiteSuite;
  private TestSuiteResultDTO lastRun;
}
