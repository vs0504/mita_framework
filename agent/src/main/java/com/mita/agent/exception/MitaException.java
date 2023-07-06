

package com.mita.agent.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MitaException extends Exception {

  private String errorCode;
  private String message;
  private String details;
  private String displayMessage;

  public MitaException() {
  }

  public MitaException(String errorCode) {
    super(errorCode);
    this.errorCode = errorCode;
  }

  public MitaException(String errorCode, Exception ex) {
    super(errorCode, ex);
    this.errorCode = errorCode;
    this.message = errorCode;
    this.displayMessage = errorCode;
  }

  public MitaException(Exception ex) {
    super(ex);
    this.message = ex.getMessage();
    this.displayMessage = ex.getLocalizedMessage();
  }

  public MitaException(String errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
    this.message = message;
    this.displayMessage = message;
  }

  public MitaException(String errorCode, String message, String details) {
    this.errorCode = errorCode;
    this.message = message;
    this.displayMessage = message;
    this.details = details;
  }
}
