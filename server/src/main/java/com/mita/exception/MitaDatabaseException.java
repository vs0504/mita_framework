package com.mita.exception;

public class MitaDatabaseException extends MitaException {
  /**
   *
   */
  private int type = 0;

  public MitaDatabaseException(String errorCode) {
    super(errorCode);
  }

  public MitaDatabaseException(String errorCode, Exception ex) {
    super(errorCode, ex);
  }

  public MitaDatabaseException(String errorCode, Exception ex, int type) {
    super(errorCode, ex);
    this.type = type;
  }

  public MitaDatabaseException(Exception ex) {
    super(ex);
  }

  public MitaDatabaseException(Exception ex, int type) {
    super(ex);
    this.type = type;
  }

  public MitaDatabaseException(String errorCode, String message) {
    super(errorCode, message);
  }

  public MitaDatabaseException(String errorCode, String message, String details) {
    super(errorCode, message, details);
  }

  public int getType() {
    return type;
  }
}

