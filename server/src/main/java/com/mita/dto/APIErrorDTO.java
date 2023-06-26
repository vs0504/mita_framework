

package com.mita.dto;

import lombok.Data;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.List;

@Data
public class APIErrorDTO {
  private Instant timeStamp;
  private String error;
  private String code;
  private List<FieldErrorDTO> fieldErrors;
  private List<ObjectErrorDTO> objectErrors;

  @PostConstruct
  private void setTimeStamp() {
    this.timeStamp = Instant.now();
  }
}
