

package com.mita.web.request;

import com.mita.model.AppPathType;
import com.mita.model.Platform;
import com.mita.model.TestPlanLabType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class TestDeviceRequest {
  Long id;
  Long agentId;
  String title;
  Long deviceId;
  Long platformOsVersionId;
  Long platformBrowserVersionId;
  Long platformScreenResolutionId;
  Long platformDeviceId;
  TestDeviceSettings settings;
  String browser;
  Platform platform;
  String browserVersion;
  String udid;
  Long appUploadId;
  Long appUploadVersionId;
  String appPackage;
  String appActivity;
  String appUrl;
  String appBundleId;
  AppPathType appPathType;
  String capabilities;
  Long envRunId;
  Boolean disable;
  Long prerequisiteTestDevicesId;
  Long prerequisiteTestDevicesIdIndex;
  Long workspaceVersionId;
  TestPlanLabType testPlanLabType;
  Boolean matchBrowserVersion = false;
  Boolean createSessionAtCaseLevel = false;
  List<Long> suiteIds;
}
