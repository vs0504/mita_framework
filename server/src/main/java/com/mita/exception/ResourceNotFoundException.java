

package com.mita.exception;

public class ResourceNotFoundException extends MitaWebException {
  public ResourceNotFoundException(String errorMessage) {
    super("0", errorMessage);
  }
}
