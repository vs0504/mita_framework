

package com.mita.web.request;

import lombok.Data;

@Data
public class VisualAnalysisRequest {
  private String baseImagePath;
  private String currentRunScreenshotPath;
  private String action;
  private String ignoreCoordinates;
  private Long screenshotResultId;

}
