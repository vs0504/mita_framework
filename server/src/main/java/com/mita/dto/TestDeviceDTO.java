

package com.mita.dto;


import com.mita.model.AppPathType;
import com.mita.model.Platform;
import com.mita.model.TestPlanLabType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class TestDeviceDTO {
  private Long id;
  private Long testPlanId;
  private String title;
  private Long agentId;
  private Long platformOsVersionId;
  private Long platformBrowserVersionId;
  private Long platformScreenResolutionId;
  private Long platformDeviceId;
  private String browser;
  private Platform platform;
  private String browserVersion;
  private String udid;
  private String appUploadId;
  private Long appUploadVersionId;
  private String appPackage;
  private String appActivity;
  private String appUrl;
  private String appBundleId;
  private AppPathType appPathType;
  private String capabilities;
  private Boolean disable;
  private Boolean matchBrowserVersion;
  private Long deviceId;
  private Boolean createSessionAtCaseLevel;
  private TestPlanLabType testPlanLabType;
  private Long workspaceVersionId;
  private Long prerequisiteTestDevicesId;
}
