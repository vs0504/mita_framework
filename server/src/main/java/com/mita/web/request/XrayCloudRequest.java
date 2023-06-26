package com.mita.web.request;

import lombok.Data;

import java.util.List;

@Data
public class XrayCloudRequest {

    private String testExecutionKey;
    private XrayInfoRequest info;
    private List<XrayTestRequest> tests;
}
