

package com.mita.mapper;

import com.mita.dto.NaturalTextActionsDTO;
import com.mita.dto.NaturaltextActionExampleDTO;
import com.mita.model.NaturalTextActionExample;
import com.mita.model.NaturalTextActions;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface NaturalTextActionMapper {
  List<NaturalTextActionsDTO> mapDTO(List<NaturalTextActions> naturalTextActions);

  NaturalTextActionsDTO mapDTO(NaturalTextActions naturalTextActions);

  NaturaltextActionExampleDTO map(NaturalTextActionExample example);
}
