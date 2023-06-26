

package com.mita.mapper;

import com.mita.dto.DefaultDataGeneratorsDTO;
import com.mita.model.DefaultDataGenerator;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DefaultDataGeneratorMapper {

  @Mapping(target = "name", source = "displayName")
  @Mapping(target = "className", source = "file.className")
  @Mapping(target = "classDisplayName", source = "file.displayName")
  DefaultDataGeneratorsDTO mapToDTO(DefaultDataGenerator defaultDataGenerator);

  List<DefaultDataGeneratorsDTO> mapToDTO(List<DefaultDataGenerator> defaultDataGeneratorList);
}
