package com.mita.exception;

public class MitaWebException extends MitaException {
  public MitaWebException(String errorCode) {
    super(errorCode);
  }

  public MitaWebException(String errorCode, String message) {
    super(errorCode, message);
  }
}

