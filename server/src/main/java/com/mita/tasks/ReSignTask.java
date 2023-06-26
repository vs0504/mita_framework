package com.mita.tasks;

import com.mita.service.ProvisioningProfileService;
import com.mita.service.ProvisioningProfileUploadService;
import com.mita.service.ResignService;
import com.mita.service.UploadVersionService;
import com.mita.model.ProvisioningProfile;
import com.mita.model.UploadType;
import com.mita.model.UploadVersion;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Log4j2
@Data
public class ReSignTask implements Runnable {
  private final WebApplicationContext webApplicationContext;
  private final ProvisioningProfileUploadService profileUploadService;
  private final ProvisioningProfileService provisioningProfileService;
  private final ResignService resignService;
  private final ProvisioningProfile profile;
  private final UploadVersionService uploadVersionService;
  private final UploadVersion uploadVersion;

  public ReSignTask(WebApplicationContext webApplicationContext,
                    ProvisioningProfile profile, UploadVersion uploadVersion) {
    super();
    this.webApplicationContext = webApplicationContext;
    this.profile = profile;
    this.uploadVersion = uploadVersion;
    this.profileUploadService = webApplicationContext.getBean(ProvisioningProfileUploadService.class);
    this.resignService = webApplicationContext.getBean(ResignService.class);
    this.uploadVersionService = webApplicationContext.getBean(UploadVersionService.class);
    this.provisioningProfileService = webApplicationContext.getBean(ProvisioningProfileService.class);
  }

  public void run() {
    if (uploadVersion != null) {
      resignUploadForAllProfiles();
    } else {
      resignAllUploads();
    }
  }

  private void resignAllUploads() {
    try {
      log.info(String.format("Resign Upload Task Started For Provision Profile [%s] - [%s] ", profile.getId(),
        profile.getName()));
      resignService.reSignWda(profile);
      resignService.reSignAllUploads(profile,
              uploadVersionService.findValidUploadsByUploadTypesIn(List.of(UploadType.IPA)));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  private void resignUploadForAllProfiles() {
    try {
      resignService.reSignForAllProfiles(uploadVersion, provisioningProfileService.findAll());
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  public Long getId() {
    return (uploadVersion != null) ? uploadVersion.getId() : profile.getId();
  }
}
