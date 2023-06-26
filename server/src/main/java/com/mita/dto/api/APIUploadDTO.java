

package com.mita.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class APIUploadDTO {
  Long id;
  @JsonProperty("created_date")
  Timestamp createdDate;
  @JsonProperty("updated_date")
  Timestamp updatedDate;
  @JsonProperty("name")
  String name;
  @JsonProperty("latest_version_id")
  Long latestVersionId;

}
