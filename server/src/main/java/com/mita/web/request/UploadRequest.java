

package com.mita.web.request;

import com.mita.model.SupportedDeviceType;
import com.mita.model.UploadType;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

@Data
public class UploadRequest {
  @NotEmpty String name;
  @NotNull UploadType uploadType;
  @Nullable MultipartFile fileContent;
  @NotNull Long workspaceId;
  SupportedDeviceType supportedDeviceType;
  String version;
}
