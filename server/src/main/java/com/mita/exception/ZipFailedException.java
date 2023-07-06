package com.mita.exception;

public class ZipFailedException extends MitaWebException {
  public ZipFailedException(String errorCode) {
    super(errorCode);
  }
}
