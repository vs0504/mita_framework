
package com.mita.exception;

public class InvalidAuthenticationConfigException extends TestsigmaWebException {

  /**
   * @param errorCode
   */
  public InvalidAuthenticationConfigException(String errorCode) {
    super(errorCode);
  }

}
