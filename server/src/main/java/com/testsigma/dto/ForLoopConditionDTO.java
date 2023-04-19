package com.testsigma.dto;

import com.testsigma.model.*;
import lombok.Data;

@Data
public class ForLoopConditionDTO {
    private Long id;

    private Long testCaseId;

    private Long testStepId;

    private Long testDataProfileId;

    private IterationType iterationType;

    private TestDataType leftParamType;

    private String leftParamValue;

    private Operator operator;

    private String rightParamValue;

    private TestDataType rightParamType;

    private Long copiedFrom;

    private Long importedId;

    private String testData;

    private Long leftFunctionId;

    private Long rightFunctionId;

    private LoopDataMapDTO leftDataMap;

    private LoopDataMapDTO rightDataMap;
}