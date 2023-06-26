package com.mita.model.recorder;

import com.mita.model.AddonActionParameterType;
import lombok.Data;

@Data
public class KibbutzPluginNLPParameterDTO {
    private Long id;
    private String name;
    private String reference;
    private String description;
    private AddonActionParameterType type;
    private String[] allowedValues;
}
