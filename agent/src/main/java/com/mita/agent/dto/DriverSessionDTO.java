

package com.mita.agent.dto;

import lombok.Data;

@Data
public class DriverSessionDTO {
  private String sessionId;
  private String status;
  private String hostname;
  private String message;
}
