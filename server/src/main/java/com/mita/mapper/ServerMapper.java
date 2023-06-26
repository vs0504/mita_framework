package com.mita.mapper;

import com.mita.dto.ServerDTO;
import com.mita.model.Server;
import com.mita.web.request.ServerRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ServerMapper {
  ServerDTO map(Server server);

  @Mapping(target = "serverUuid", ignore = true)
  @Mapping(target = "serverOs", ignore = true)
  void merge(ServerRequest serverRequest, @MappingTarget Server server);
}
