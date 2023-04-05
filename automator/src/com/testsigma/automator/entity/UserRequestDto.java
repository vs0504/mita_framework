package com.testsigma.automator.entity;

import lombok.Data;

@Data
public class UserRequestDto {
    private String requestsrc="ui";
    private String requesttype="listdata";

    private String requestid = "21835674";

    private UserRequest requestdata = new UserRequest() ;


}
