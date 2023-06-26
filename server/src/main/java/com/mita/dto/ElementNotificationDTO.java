package com.mita.dto;


import lombok.Data;

@Data
public class ElementNotificationDTO {

  private Long id;

  private String name;

  private Long assignee;

  private Long reviewedBy;

  private UserNotificationDTO from;

  private UserNotificationDTO to;

  private String comments;

  private String url;

  private String submittedFromUrl;

  private boolean testCaseUrl;

  private boolean testCaseResultUrl;

  private ElementNotificationDTO elementNotificationDTO;

  private String screenName;

  private String screenNameUrl;

  private String screenUrl;

}
