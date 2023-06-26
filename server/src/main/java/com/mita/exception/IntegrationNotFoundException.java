
package com.mita.exception;

public class IntegrationNotFoundException extends TestsigmaWebException {

  /**
   * @param errorCode
   */
  public IntegrationNotFoundException(String errorCode) {
    super(errorCode);
  }

}
