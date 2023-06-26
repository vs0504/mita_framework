package com.mita.mapper;


import com.mita.config.AdditionalPropertiesConfig;
import com.mita.dto.AuthenticationConfigDTO;
import com.mita.web.request.AuthenticationConfigRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AuthenticationConfigMapper {

  AuthenticationConfigDTO map(AdditionalPropertiesConfig config);

  void merge(AuthenticationConfigRequest authenticationConfigRequest, @MappingTarget AdditionalPropertiesConfig additionalPropertiesConfig);
}
