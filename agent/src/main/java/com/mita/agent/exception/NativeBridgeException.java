

package com.mita.agent.exception;

public class NativeBridgeException extends Exception {
  public NativeBridgeException(String description) {
    super(description);
  }

  public NativeBridgeException(String description, Throwable e) {
    super(description, e);
  }
}
