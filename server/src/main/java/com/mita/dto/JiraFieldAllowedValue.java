

package com.mita.dto;

import lombok.Data;

import java.util.Map;

@Data
public class JiraFieldAllowedValue {
  String id;
  String iconUrl;
  String name;
  String value;
  String key;
  String description;
  Map<String, String> avatarUrls;
}
