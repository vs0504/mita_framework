

package com.mita.agent.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MobileOs {
  ANDROID("Android", "UiAutomator2"),
  IOS("iOS", "XCUITest");

  @Getter
  private final String platformName;

  @Getter
  private final String automationName;
}
