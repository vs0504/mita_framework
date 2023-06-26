

package com.mita.mapper;

import com.mita.dto.ElementFilterDTO;
import com.mita.model.ElementFilter;
import com.mita.web.request.ElementFilterRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ElementFilterMapper {
  ElementFilter map(ElementFilterRequest request);

  void merge(@MappingTarget ElementFilter elementFilter, ElementFilterRequest request);

  List<ElementFilterDTO> map(List<ElementFilter> elementFilters);

  ElementFilterDTO map(ElementFilter elementFilter);

}
