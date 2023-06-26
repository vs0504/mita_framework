

package com.mita.mapper;

import com.mita.dto.PrivateGridNodeDTO;
import com.mita.model.PrivateGridNode;
import com.mita.web.request.PrivateGridNodeRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PrivateGridNodeMapper {

  @Mapping(target = "browserList", expression = "java(nodeRequest.getNodeBrowserList())")
  PrivateGridNode map(PrivateGridNodeRequest nodeRequest);

  @Mapping(target = "browserList", expression = "java(nodeRequest.getNodeBrowserList())")
  void map(PrivateGridNodeRequest nodeRequest, @MappingTarget PrivateGridNode node);

  @Mapping(target = "browserList", expression = "java(node.getBrowserListDTO())")
  PrivateGridNodeDTO map(PrivateGridNode node);

  List<PrivateGridNodeDTO> mapList(List<PrivateGridNode> node);

}
