package com.mita.mapper;

import com.mita.dto.ChromeExtensionDetailsDTO;
import com.mita.model.ChromeExtensionDetails;
import com.mita.web.request.ChromeExtensionDetailsRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ChromeExtensionDetailsMapper {

  ChromeExtensionDetailsDTO map(ChromeExtensionDetails chromeExtensionDetails);

  ChromeExtensionDetails map(ChromeExtensionDetailsRequest chromeExtensionDetailsRequest);

  void merge(ChromeExtensionDetailsRequest chromeExtensionDetailsRequest, @MappingTarget ChromeExtensionDetails chromeExtensionDetails);
}
