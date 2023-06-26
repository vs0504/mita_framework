package com.mita.web.request;

import lombok.Data;

import java.util.List;


@Data
public class XrayTestRequest {
    private String testKey;
    private String start;
    private String finish;
    private String status;
    private List<XrayIterationRequest> iterations;
}