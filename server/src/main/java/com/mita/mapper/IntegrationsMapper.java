
package com.mita.mapper;

import com.mita.dto.IntegrationsDTO;
import com.mita.model.Integrations;
import com.mita.web.request.IntegrationsRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IntegrationsMapper {
  IntegrationsDTO map(Integrations entity);

  @Mapping(target = "workspace", expression = "java(com.mita.model.Integration.getIntegration(request.getWorkspaceId()))")
  Integrations map(IntegrationsRequest request);

  List<IntegrationsDTO> map(List<Integrations> configs);
}
