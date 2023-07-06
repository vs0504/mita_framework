
package com.mita.exception;

public class InsecureAuthenticationException extends MitaWebException {

  /**
   * @param errorCode
   */
  public InsecureAuthenticationException(String errorCode) {
    super(errorCode);
  }

  public InsecureAuthenticationException(String errorCode, String message) {
    super(errorCode, message);
  }
}
