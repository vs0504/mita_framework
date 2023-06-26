package com.mita.automator.exceptions;

import java.io.IOException;

public class AgentDeletedException extends IOException {
  public AgentDeletedException() {
    super("Agent is Deleted");
  }
}
