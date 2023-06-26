

package com.mita.exception;

import org.springframework.security.core.AuthenticationException;

public class InValidJwtTokenException extends AuthenticationException {
  public InValidJwtTokenException(String msg) {
    super(msg);
  }
}
