

package com.mita.dto;

import lombok.Data;

@Data
public class SessionDTO {
  String id;
  AuthUserDTO user;
  private String serverUrl;
}
