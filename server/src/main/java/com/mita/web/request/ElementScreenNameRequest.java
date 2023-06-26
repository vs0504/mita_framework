

package com.mita.web.request;

import lombok.Data;

import java.io.Serializable;

@Data

public class ElementScreenNameRequest implements Serializable {
  private Long id;
  private Long workspaceVersionId;
  private String name;
}
