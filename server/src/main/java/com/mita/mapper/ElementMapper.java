

package com.mita.mapper;

import com.mita.dto.api.APIElementDTO;
import com.mita.dto.ElementDTO;
import com.mita.dto.ElementNotificationDTO;
import com.mita.dto.export.ElementCloudXMLDTO;
import com.mita.dto.export.ElementXMLDTO;
import com.mita.model.Element;
import com.mita.model.ElementMetaData;
import com.mita.model.ElementMetaDataRequest;
import com.mita.web.request.ElementRequest;
import com.mita.web.request.testproject.TestProjectElementRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ElementMapper {

  List<ElementXMLDTO> mapElements(List<Element> elementsList);

  @Mapping(target = "currentElement", expression = "java(elementMetaDataRequest.getStringCurrentElement())")
  ElementMetaData map(ElementMetaDataRequest elementMetaDataRequest);

  ElementDTO map(Element element);
  APIElementDTO mapToApi(Element element);

  List<APIElementDTO> mapToApiList(List<Element> element);

  List<ElementDTO> map(List<Element> elementList);

  @Mapping(target = "screenNameObj", ignore = true)
  void merge(ElementRequest elementRequest, @MappingTarget Element element);

  @Mapping(target = "screenNameObj", ignore = true)
  Element map(ElementRequest elementRequest);

  @Mapping(target = "locatorValue", expression = "java(testProjectElementRequest.getLocators().get(0).getValue())")
  @Mapping(target = "type", expression = "java(testProjectElementRequest.getLocators().get(0).getLocatorType().getId())")
  Element map(TestProjectElementRequest testProjectElementRequest);

  @Mapping(target = "screenName", expression = "java(element.getScreenNameObj().equals(null)? null: element.getScreenNameObj().getName())")
  ElementNotificationDTO mapNotificationDTO(Element element);

  List<Element> mapElementsList(List<ElementXMLDTO> readValue);
  List<Element> mapCloudElementsList(List<ElementCloudXMLDTO> readValue);

  Element copy(Element element);
}
