

package com.mita.mapper;

import com.mita.dto.ProvisioningProfileDTO;
import com.mita.model.ProvisioningProfile;
import com.mita.web.request.ProvisioningProfileRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProvisioningProfileMapper {

  ProvisioningProfile map(ProvisioningProfileRequest system);

  ProvisioningProfileDTO map(ProvisioningProfile system);

  List<ProvisioningProfileDTO> map(List<ProvisioningProfile> systems);

  void merge(@MappingTarget ProvisioningProfile profile, ProvisioningProfileRequest request);
}
