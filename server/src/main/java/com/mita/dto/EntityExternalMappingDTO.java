

package com.mita.dto;

import com.mita.model.EntityType;
import lombok.Data;

import java.util.Map;

@Data
public class EntityExternalMappingDTO {

  Long id;
  EntityType entityType;
  Long entityId;
  Long applicationId;
  String externalId;
  Map<String, Object> fields;
  Boolean pushFailed;
  String message;
}
