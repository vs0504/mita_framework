

package com.mita.exception;

public class ResourceNotFoundException extends TestsigmaWebException {
  public ResourceNotFoundException(String errorMessage) {
    super("0", errorMessage);
  }
}
