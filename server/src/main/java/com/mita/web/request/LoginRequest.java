
package com.mita.web.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
  @NotBlank
  @Email
  String username;
  @NotBlank
  String password;
}
