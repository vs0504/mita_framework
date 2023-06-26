

package com.mita.dto;

import com.mita.model.SupportedDeviceType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UploadDTO {
  Long id;
  Timestamp createdDate;
  Timestamp updatedDate;
  SupportedDeviceType supportedDeviceType;
  String name;
  Long latestVersionId;
}
