package com.mita.agent.utils;

import lombok.Data;

@Data
public class ResponseObject {

    private int responseCode;
    private String responseMessage;
    private String statusMessage;


    public ResponseObject(int responseCode, String responseMessage, String statusMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.statusMessage = statusMessage;
    }

}