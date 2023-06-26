

package com.mita.dto;

import lombok.Data;

@Data
public class ImportNotificationDTO {
  Integer failedCount;
  Integer totalCount;
  String url;
  Boolean success;
}
