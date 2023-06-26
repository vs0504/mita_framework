package com.mita.service;

import com.mita.constants.TSCapabilityType;
import com.mita.exception.ResourceNotFoundException;
import com.mita.exception.TestsigmaException;
import com.mita.config.StorageServiceFactory;
import com.mita.model.*;
import com.mita.tasks.PlatformAppUploader;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public abstract class MobileCapabilities extends Capabilities {
  protected String fileName;
  @Autowired
  protected UploadService uploadService;
  @Autowired
  protected UploadVersionService uploadVersionService;
  @Autowired
  protected StorageServiceFactory storageServiceFactory;
  @Autowired
  protected StorageServiceFactory storageService;
  @Autowired
  protected ResignService resignService;
  @Autowired
  protected ProvisioningProfileUploadService provisioningProfileUploadService;
  @Autowired
  protected ProvisioningProfileService provisioningProfileService;
  @Autowired
  protected ProvisioningProfileDeviceService provisioningProfileDeviceService;
  @Autowired
  PlatformAppUploader platformAppUploader;


  public String getPreSignedUrl(TestDevice testDevice) throws ResourceNotFoundException {
    Upload upload = this.uploadService.find(Long.valueOf(testDevice.getAppUploadId()));
    UploadVersion uploadVersion = testDevice.getAppUploadVersionId() == null ? upload.getLatestVersion() : uploadVersionService.find(testDevice.getAppUploadVersionId());
    Optional<URL> newPreSignedURL =
      this.storageServiceFactory.getStorageService().generatePreSignedURLIfExists(uploadVersion.getPath(),
        StorageAccessLevel.READ, 300
      );
    return newPreSignedURL.get().toString();
  }

  public String copyUploadToLocal(TestDevice testDevice) throws TestsigmaException {
    UploadVersion upload = this.uploadVersionService.find(testDevice.getAppUploadVersionId());
    return storageServiceFactory.getStorageService().downloadToLocal(upload.getPath(),
      StorageAccessLevel.READ);
  }


  public void setTestsigmaLabAppCapability(TestDevice testDevice, AppPathType pathType,
                                           Integrations integrations,
                                           List<WebDriverCapability> capabilities) throws TestsigmaException {
    AppPathType appPathType = pathType;
    String platformAppId = null;
    String appLocalPath;
    if (AppPathType.USE_PATH == appPathType || AppPathType.UPLOADS == appPathType) {
      log.info("Found an APP_PATH / UPLOAD Id as capability. Uploading it and using it");
      if (testDevice.getAppUrl() != null) {
        appLocalPath = storageServiceFactory.getStorageService().downloadFromRemoteUrl(testDevice.getAppUrl());
      } else {
        appLocalPath = copyUploadToLocal(testDevice);
      }
      platformAppId = platformAppUploader.uploadAppToTestsigmaLab(
        integrations.getPassword(), appLocalPath);
      log.info("Finished uploading app, using app Id: " + platformAppId);
    } else if (AppPathType.APP_DETAILS == appPathType) {
      if (testDevice.getAppUrl() != null) {
        platformAppId = testDevice.getAppUrl();
      }
      log.info("Using External AppId as Capability: " + platformAppId);
    }
    capabilities.add(new WebDriverCapability(TSCapabilityType.APP, platformAppId));
  }

  @Override
  public void setHybridCapabilities(TestDevice testDevice,
                                    Integrations integrations,
                                    List<WebDriverCapability> capabilities,
                                    TestPlanLabType testPlanLabType)
    throws TestsigmaException {

  }

  @Override
  public void setTestsigmaLabCapabilities(TestDevice testDevice,
                                          Integrations integrations,
                                          List<WebDriverCapability> capabilities)
    throws TestsigmaException {

  }
}