

package com.mita.web.request;

import lombok.Data;

@Data
public class TestStepScreenshotRequest {
  Long id;
  Long testStepId;
  Long testStepResultId;
  Long testCaseResultId;
  Long environmentResultId;
  String ignoredCoordinates;
  String baseImageName;
  String screenResolution;
  String browser;
  Double browserVersion;
  String deviceName;
  String deviceOsVersion;
}
