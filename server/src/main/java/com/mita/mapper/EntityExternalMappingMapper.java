package com.mita.mapper;

import com.mita.dto.EntityExternalMappingDTO;
import com.mita.model.EntityExternalMapping;
import com.mita.web.request.EntityExternalMappingRequest;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EntityExternalMappingMapper {

  EntityExternalMapping map(EntityExternalMappingRequest request);

  List<EntityExternalMappingDTO> mapToDTO(List<EntityExternalMapping> mappings);

  EntityExternalMappingDTO mapToDTO(EntityExternalMapping mapping);
}
