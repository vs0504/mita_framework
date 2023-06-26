

package com.mita.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ElementFilterDTO {
  Long id;
  Long versionId;
  String name;
  Boolean isPublic;
  Boolean isDefault;
  String queryHash;
  Timestamp createdDate;
  Timestamp updatedDate;
}
