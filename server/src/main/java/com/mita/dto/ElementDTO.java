

package com.mita.dto;

import com.mita.model.ElementCreateType;
import com.mita.model.LocatorType;
import com.mita.model.recorder.UiIdentifierScreenNameDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode
public class ElementDTO {
  private Long id;
  private Long workspaceVersionId;
  private String locatorValue;
  private String name;
  private Integer type;
  private ElementCreateType createdType;
  private LocatorType locatorType;
  private ElementMetaDataDTO metadata;
  private String attributes;
  private Boolean isDynamic;
  private Timestamp createdDate;
  private Timestamp updatedDate;
  private Long screenNameId;
  private ElementScreenNameDTO screenNameObj;
  private Boolean isDuplicated;

  public UiIdentifierScreenNameDTO getUiIdentifierScreenNameObject() {
    return new UiIdentifierScreenNameDTO();
  }
}
