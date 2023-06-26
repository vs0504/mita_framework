package com.mita.mapper;

import com.mita.dto.MobileInspectionDTO;
import com.mita.model.MobileInspection;
import com.mita.web.request.MobileInspectionRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MobileInspectionMapper {
  MobileInspectionDTO mapDTO(MobileInspection mobileInspection);

  List<MobileInspectionDTO> mapDTO(List<MobileInspection> mobileInspections);

  MobileInspection map(MobileInspectionRequest request);

  void merge(MobileInspectionRequest request, @MappingTarget MobileInspection mobileInspection);

}
