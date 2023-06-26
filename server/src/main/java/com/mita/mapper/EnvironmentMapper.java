

package com.mita.mapper;

import com.mita.dto.api.APIEnvironmentDTO;
import com.mita.dto.EnvironmentDTO;
import com.mita.model.Environment;
import com.mita.web.request.EnvironmentRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EnvironmentMapper {
  EnvironmentDTO map(Environment environment);
  APIEnvironmentDTO mapApi(Environment environment);

  List<EnvironmentDTO> map(List<Environment> environment);
  List<APIEnvironmentDTO> mapApi(List<Environment> environment);

  default void merge(Environment environment, EnvironmentRequest request) {
    if (request == null) {
      return;
    }

    if (request.getParameters() != null) {
      environment.setParameters(request.getParameters());
    }

    if (request.getId() != null) {
      environment.setId(request.getId());
    }
    if (request.getName() != null) {
      environment.setName(request.getName());
    }
    if (request.getDescription() != null) {
      environment.setDescription(request.getDescription());
    }

  }

  Environment map(EnvironmentRequest environmentRequest);
}
