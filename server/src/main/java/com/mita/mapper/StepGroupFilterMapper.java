

package com.mita.mapper;

import com.mita.dto.StepGroupFilterDTO;
import com.mita.model.StepGroupFilter;
import com.mita.web.request.StepGroupFilterRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface StepGroupFilterMapper {
  StepGroupFilter map(StepGroupFilterRequest request);

  void merge(@MappingTarget StepGroupFilter filter, StepGroupFilterRequest request);

  List<StepGroupFilterDTO> map(List<StepGroupFilter> filters);

  StepGroupFilterDTO map(StepGroupFilter filter);

}
