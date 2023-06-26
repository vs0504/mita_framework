

package com.mita.web.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class StepGroupFilterRequest {
  Long id;
  Long versionId;
  String name;
  Boolean isPublic;
  Boolean isDefault;
  String queryHash;
  Timestamp createdDate;
  Timestamp updatedDate;
}
