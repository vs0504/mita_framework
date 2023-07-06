
package com.mita.exception;

public class InvalidAuthenticationConfigException extends MitaWebException {

  /**
   * @param errorCode
   */
  public InvalidAuthenticationConfigException(String errorCode) {
    super(errorCode);
  }

}
