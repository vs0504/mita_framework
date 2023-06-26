

package com.mita.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ElementScreenNameDTO {
  private Long id;
  private Long workspaceVersionId;
  private String name;
}
