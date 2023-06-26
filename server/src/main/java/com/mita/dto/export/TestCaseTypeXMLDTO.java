

package com.mita.dto.export;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.mita.annotation.JsonListRootName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonListRootName(name = "test-case-types")
@JsonRootName(value = "test-case-type")
public class TestCaseTypeXMLDTO extends BaseXMLDTO {
  @JsonProperty("id")
  private Long id;
  @JsonProperty("record-active")
  private Boolean recordActive = true;
  @JsonProperty("display-name")
  private String displayName;
  @JsonProperty("name")
  private String name;
  @JsonProperty("project-id")
  private Long projectId;
  @JsonProperty("created-by-id")
  private Long createdById;
  @JsonProperty("created-date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
  private Timestamp createdDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
  @JsonProperty("updated-date")
  private Timestamp updatedDate;
  @JsonProperty("is-default")
  private Boolean isDefault = false;
}