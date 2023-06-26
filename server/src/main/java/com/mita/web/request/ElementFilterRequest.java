

package com.mita.web.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ElementFilterRequest {
  Long id;
  Long versionId;
  String name;
  Boolean isPublic;
  Boolean isDefault;
  String queryHash;
  Timestamp createdDate;
  Timestamp updatedDate;
}
