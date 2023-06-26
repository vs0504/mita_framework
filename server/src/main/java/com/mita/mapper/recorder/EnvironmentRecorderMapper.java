package com.mita.mapper.recorder;

import com.mita.model.recorder.EnvironmentDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EnvironmentRecorderMapper {

    @Mapping(target = "updatedById", ignore = true)
    @Mapping(target = "passwords", ignore = true)
    @Mapping(target = "createdById", ignore = true)
    EnvironmentDTO mapDTO(com.mita.dto.EnvironmentDTO environmentDTO);

    List<EnvironmentDTO> mapDTOs(List<com.mita.dto.EnvironmentDTO> environmentDTOs);
}
