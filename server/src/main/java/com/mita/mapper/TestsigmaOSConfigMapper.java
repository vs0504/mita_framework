

package com.mita.mapper;

import com.mita.dto.OpensourceDTO;
import com.mita.model.TestsigmaOSConfig;
import com.mita.web.request.OpensourceRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TestsigmaOSConfigMapper {

  OpensourceDTO map(TestsigmaOSConfig testsigmaOSConfig);

  void merge(OpensourceRequest request, @MappingTarget TestsigmaOSConfig testsigmaOSConfig);

}
