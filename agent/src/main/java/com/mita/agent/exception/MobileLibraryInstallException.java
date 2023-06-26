

package com.mita.agent.exception;

public class MobileLibraryInstallException extends Exception {
  public MobileLibraryInstallException(String description) {
    super(description);
  }

  public MobileLibraryInstallException(String description, Throwable e) {
    super(description, e);
  }
}
