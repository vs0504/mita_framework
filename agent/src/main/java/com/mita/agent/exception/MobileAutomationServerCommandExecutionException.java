

package com.mita.agent.exception;

public class MobileAutomationServerCommandExecutionException extends Exception {
  public MobileAutomationServerCommandExecutionException(String description) {
    super(description);
  }

  public MobileAutomationServerCommandExecutionException(String description, Throwable e) {
    super(description, e);
  }
}
