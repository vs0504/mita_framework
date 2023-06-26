

package com.mita.agent.dto;

import lombok.Data;

@Data
public class ObjectErrorDTO {
  private String objectName;
  private Object source;
  private String defaultMessage;
  private String[] codes;
}
