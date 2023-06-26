

package com.mita.web.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProvisioningProfileRequest {
  private Long id;
  private String name;
  private String teamId;
  private MultipartFile cer;
  private MultipartFile provisioningProfile;
}
