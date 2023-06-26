

package com.mita.web.request;

import com.mita.dto.ElementScreenNameDTO;
import com.mita.model.ElementCreateType;
import com.mita.model.ElementMetaDataRequest;
import com.mita.model.LocatorType;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Log4j2
public class ElementRequest {
  @NotEmpty
  private String name;
  @NotNull
  private ElementCreateType createdType;
  @NotNull
  private LocatorType locatorType;
  private String locatorValue;
  private String attributes;
  private ElementMetaDataRequest metadata;
  private Boolean isDynamic = Boolean.FALSE;
  @NotNull
  private Long workspaceVersionId;
  private Long screenNameId;
  private ElementScreenNameDTO screenNameObj;
}
