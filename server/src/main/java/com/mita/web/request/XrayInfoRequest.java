package com.mita.web.request;

import lombok.Data;

@Data
public class XrayInfoRequest {

    private String summary;
    private String description;
    private String user;
    private String testPlanKey;
}
