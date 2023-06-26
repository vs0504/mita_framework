

package com.mita.mapper;

import com.mita.dto.SuggestionResultMappingDTO;
import com.mita.model.SuggestionResultMapping;
import com.mita.web.request.SuggestionEngineResultRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SuggestionResultMappingMapper {
  @Mapping(target = "metaData", expression = "java(request.getMetaData())")
  SuggestionResultMapping map(SuggestionEngineResultRequest request);

  List<SuggestionResultMappingDTO> map(List<SuggestionResultMapping> suggestionResultMappings);

  SuggestionResultMappingDTO map(SuggestionResultMapping result);
}
