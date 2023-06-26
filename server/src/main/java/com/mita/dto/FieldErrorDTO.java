

package com.mita.dto;

import lombok.Data;

@Data
public class FieldErrorDTO {
  private String field;
  private Object rejectedValue;
  private String message;
  private Boolean bindingFailure;
  private String[] codes;
}
