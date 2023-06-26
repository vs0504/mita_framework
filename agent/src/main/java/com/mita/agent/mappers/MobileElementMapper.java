

package com.mita.agent.mappers;

import com.mita.agent.dto.MobileElementDTO;
import com.mita.automator.actions.mobile.MobileElement;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MobileElementMapper {
  MobileElementDTO map(MobileElement mobileElement);

  List<MobileElementDTO> map(List<MobileElement> mobileElements);
}
