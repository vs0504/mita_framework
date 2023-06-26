

package com.mita.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MobileOs {
  ANDROID,
  IOS;

  public Platform getPlatform() {
    if (this == ANDROID)
      return Platform.Android;
    else
      return Platform.iOS;
  }
}
