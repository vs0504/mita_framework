
package com.mita.exception;

public class IntegrationNotFoundException extends MitaWebException {

  /**
   * @param errorCode
   */
  public IntegrationNotFoundException(String errorCode) {
    super(errorCode);
  }

}
