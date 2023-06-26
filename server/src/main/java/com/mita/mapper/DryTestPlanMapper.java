package com.mita.mapper;

import com.mita.web.request.TestDeviceSettings;
import com.mita.dto.DryTestPlanDTO;
import com.mita.model.DryTestPlan;
import com.mita.web.request.DryTestPlanRequest;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DryTestPlanMapper {

  DryTestPlan map(DryTestPlanRequest dryTestPlanRequest);

  com.mita.model.TestDeviceSettings map(TestDeviceSettings settings);

  List<DryTestPlanDTO> mapList(List<DryTestPlan> content);
}
