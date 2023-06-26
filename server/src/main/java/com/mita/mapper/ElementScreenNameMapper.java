

package com.mita.mapper;

import com.mita.dto.ElementScreenNameDTO;
import com.mita.dto.export.ElementScreenNameCloudXMLDTO;
import com.mita.dto.export.ElementScreenNameXMLDTO;
import com.mita.model.ElementScreenName;
import com.mita.web.request.ElementScreenNameRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ElementScreenNameMapper {

  List<ElementScreenNameXMLDTO> mapElementScreenNameList(List<ElementScreenName> elementScreenNames);

  @IterableMapping(qualifiedByName = "mapData")
  List<ElementScreenNameDTO> map(List<ElementScreenName> screenNames);

  ElementScreenName map(ElementScreenNameRequest screenNameRequest);

  @Named("mapData")
  ElementScreenNameDTO map(ElementScreenName screenName);

    List<ElementScreenName> mapElementScreenNamesList(List<ElementScreenNameXMLDTO> readValue);
    List<ElementScreenName> mapCloudElementScreenNamesList(List<ElementScreenNameCloudXMLDTO> readValue);

  ElementScreenName copy(ElementScreenName uiIdentifier);
}
