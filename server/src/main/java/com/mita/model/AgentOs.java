

package com.mita.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AgentOs {
  WINDOWS,
  MACOSX,
  LINUX;

  public Platform getPlatform() {
    switch (this) {
      case WINDOWS:
        return Platform.Windows;
      case MACOSX:
        return Platform.Mac;
      case LINUX:
        return Platform.Linux;
    }
    return null;
  }
}
