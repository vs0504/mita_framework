

package com.mita.agent.exception;

public class MobileAutomationServerSessionException extends Exception {
  public MobileAutomationServerSessionException(String description) {
    super(description);
  }

  public MobileAutomationServerSessionException(String description, Throwable e) {
    super(description, e);
  }
}
