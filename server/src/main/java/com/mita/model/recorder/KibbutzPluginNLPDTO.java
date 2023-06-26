package com.mita.model.recorder;

import com.mita.model.StepActionType;
import com.mita.model.WorkspaceType;
import lombok.Data;

import java.util.List;

@Data
public class KibbutzPluginNLPDTO {
    private Long id;
    private String grammar;
    private String description;
    private WorkspaceType applicationType;
    private Boolean deprecated;
    private List<KibbutzPluginNLPParameterDTO> parameters;
    private StepActionType stepActionType;
}
