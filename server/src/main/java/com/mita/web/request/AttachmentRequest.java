

package com.mita.web.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AttachmentRequest {
  @NotNull Long entityId;
  @NotEmpty String name;
  @NotNull MultipartFile fileContent;
  @NotNull
  private String entity;
  private String description;
}
