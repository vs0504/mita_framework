

package com.mita.dto;

import com.mita.model.UploadStatus;
import com.mita.model.UploadType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UploadVersionDTO {
  Long id;
  Timestamp createdDate;
  Timestamp updatedDate;
  String name;
  String path;
  String fileName;
  UploadType type;
  String version;
  Long fileSize;
  String preSignedURL;
  Boolean signed = Boolean.FALSE;
  UploadStatus uploadStatus;
  Long uploadId;
}
