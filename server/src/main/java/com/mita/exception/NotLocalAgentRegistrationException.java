package com.mita.exception;

public class NotLocalAgentRegistrationException extends MitaException {
  public NotLocalAgentRegistrationException(String errorCode) {
    super(errorCode);
  }

  public NotLocalAgentRegistrationException(String errorCode, String message) {
    super(errorCode, message);
  }
}
