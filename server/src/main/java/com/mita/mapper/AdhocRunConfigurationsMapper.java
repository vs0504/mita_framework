package com.mita.mapper;

import com.mita.dto.AdhocRunConfigurationDTO;
import com.mita.model.AdhocRunConfiguration;
import com.mita.web.request.AdhocRunConfigurationRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AdhocRunConfigurationsMapper {
  AdhocRunConfiguration map(AdhocRunConfigurationRequest adhocRunConfigurationRequest);

  void map(AdhocRunConfigurationRequest adhocRunConfigurationRequest,
           @MappingTarget AdhocRunConfiguration target);

  List<AdhocRunConfigurationDTO> map(List<AdhocRunConfiguration> configurations);

  //  @Mapping(target = "workspaceType", expression="java(adhocRunConfiguration.getWorkspaceType().getId())")
  AdhocRunConfigurationDTO map(AdhocRunConfiguration adhocRunConfiguration);
}
