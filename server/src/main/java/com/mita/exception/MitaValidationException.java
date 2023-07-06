package com.mita.exception;

public class MitaValidationException extends MitaException {

  public MitaValidationException(String errorCode) {
    super(errorCode);
  }

  public MitaValidationException(String errorCode, String message) {
    super(errorCode, message);
  }
}

