

package com.mita.dto;

import lombok.Data;

import java.util.List;

@Data
public class JiraIssueFieldDTO {
  String name;
  String key;
  Boolean hasDefaultValue;
  Boolean required;
  String autoCompleteUrl;
  JiraIssueFieldSchemaDTO schema;
  List<JiraFieldAllowedValue> allowedValues;
}
