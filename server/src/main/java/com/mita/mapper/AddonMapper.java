

package com.mita.mapper;

import com.mita.dto.AddonNaturalTextActionDTO;
import com.mita.dto.AddonNaturalTextActionParameterEntityDTO;
import com.mita.dto.AddonPluginTestDataFunctionDTO;
import com.mita.dto.AddonPluginTestDataFunctionParameterEntityDTO;
import com.mita.model.*;
import com.mita.web.request.AddonNaturalTextActionRequest;
import com.mita.web.request.AddonRequest;
import com.mita.web.request.AddonNaturalTextActionParameterRequest;
import com.mita.web.request.AddonPluginTestDataFunctionRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AddonMapper {
  AddonNaturalTextActionDTO map(AddonNaturalTextAction addonNaturalTextAction);

  AddonPluginTestDataFunctionDTO map(AddonPluginTestDataFunction addonNaturalTextAction);

  Addon mapRequest(AddonRequest request);

  List<AddonNaturalTextAction> mapAction(List<AddonNaturalTextActionRequest> actionRequests);

  List<AddonNaturalTextActionParameter> mapParams(List<AddonNaturalTextActionParameterRequest> parameterRequests);

  List<AddonNaturalTextActionParameterEntityDTO> mapParamsEntity(List<AddonNaturalTextActionParameter> parameters);

  List<AddonPluginTestDataFunctionParameterEntityDTO> mapTDFParamsEntity(List<AddonPluginTestDataFunctionParameter> parameters);

  @Mapping(target = "workspaceType", expression = "java(actionRequest.getWorkspaceType().getWorkspaceType())")
  AddonNaturalTextAction mapAction(AddonNaturalTextActionRequest actionRequest);

  AddonPluginTestDataFunction mapTestDataFunction(AddonPluginTestDataFunctionRequest tdfRequest);

  void merge(Addon plugin, @MappingTarget Addon dbPlugin);

  void merge(AddonNaturalTextAction action, @MappingTarget AddonNaturalTextAction actionDB);

  void merge(AddonPluginTestDataFunction tdf, @MappingTarget AddonPluginTestDataFunction tdfDB);

  List<AddonNaturalTextActionDTO> mapToDTO(List<AddonNaturalTextAction> actions);

  List<AddonPluginTestDataFunctionDTO> mapTDFToDTO(List<AddonPluginTestDataFunction> tdf);

}
