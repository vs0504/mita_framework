

package com.mita.dto;

import lombok.Data;

@Data
public class StepResultScreenshotComparisonDTO {
  Long id;
  Long testStepId;
  Long testStepResultId;
  Long testStepBaseScreenshotId;
  Double similarityScore;
  String diffCoordinates;
  String imageShape;
  String errorMessage;
  String screenShotURL;
  TestStepResultDTO testStepResult;
  TestStepScreenshotDTO testStepScreenshot;
}
