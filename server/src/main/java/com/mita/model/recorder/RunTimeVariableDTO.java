package com.mita.model.recorder;

import lombok.Data;

@Data
public class RunTimeVariableDTO {

    private String runTimeVariableName;
    private String testCaseName;
    private Integer stepPosition;
    private Long testCaseId;
    private Long stepId;
}
