

package com.mita.agent.exception;

public class AdbCommandExecutionException extends Exception {

  public AdbCommandExecutionException(String description, Throwable e) {
    super(description, e);
  }
}
