package com.mita.dto;

import lombok.Data;

@Data
public class TestPlanEmailDTO {

    private String emailList;
    private Long testPlanId;
    private Long runId;
    private String plannedTime;
}
