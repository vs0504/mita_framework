
package com.mita.mapper;

import com.mita.dto.UserPreferenceDTO;
import com.mita.model.UserPreference;
import com.mita.web.request.UserPreferenceRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserPreferenceMapper {
  UserPreferenceDTO map(UserPreference userPreference);

  void merge(UserPreferenceRequest userPreferenceRequest
    , @MappingTarget UserPreference userPreference);
}
