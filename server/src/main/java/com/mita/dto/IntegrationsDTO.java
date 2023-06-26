
package com.mita.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mita.model.Integration;
import com.mita.model.IntegrationMetaData;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class IntegrationsDTO {
  @JsonProperty("id")
  private Long id;

  private String name;

  @JsonProperty("username")
  private String username;


  private String password;

  private String token;

  private String description;

  @JsonProperty("workspace")
  private Integration workspace;

  private String url;

  private IntegrationMetaData metadata;

  private Timestamp createdDate;
  private Timestamp updatedDate;

}
