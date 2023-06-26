package com.mita.mapper.recorder;

import java.util.List;
import com.mita.dto.AddonNaturalTextActionDTO;
import com.mita.dto.AddonNaturalTextActionParameterDTO;
import com.mita.dto.AddonPluginTestDataFunctionDTO;
import com.mita.model.recorder.KibbutzPluginNLPDTO;
import com.mita.model.recorder.KibbutzPluginNLPParameterDTO;
import com.mita.model.recorder.KibbutzPluginTestDataFunctionDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface KibbutzPluginNLPMapper {

    @Mapping(target = "applicationType", source = "workspaceType")
    @Mapping(target = "parameters", expression = "java(mapNLPParameter(dto.getParameters()))")
    KibbutzPluginNLPDTO mapKibbutzPluginNLPDTOs(AddonNaturalTextActionDTO dto);

    List<KibbutzPluginNLPDTO> mapKibbutzPluginNLPDTOs(List<AddonNaturalTextActionDTO> dtos);

    @Mapping(target = "allowedValues", expression = "java(parameter.getAllowedValues() != null ? (String[]) parameter.getAllowedValues().toArray() : null)")
    KibbutzPluginNLPParameterDTO mapNLPParameter(AddonNaturalTextActionParameterDTO parameter);

    List<KibbutzPluginNLPParameterDTO> mapNLPParameter(List<AddonNaturalTextActionParameterDTO> parameters);

    @Mapping(source = "addonId", target = "pluginId")
    KibbutzPluginTestDataFunctionDTO mapPluginTestDataFunctionDTO(AddonPluginTestDataFunctionDTO addonPluginTestDataFunctionDTO);

    List<KibbutzPluginTestDataFunctionDTO> mapPluginTestDataFunctionDTOs(List<AddonPluginTestDataFunctionDTO> addonPluginTestDataFunctionDTOs);
}
