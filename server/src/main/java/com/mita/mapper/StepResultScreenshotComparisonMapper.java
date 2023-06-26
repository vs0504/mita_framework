

package com.mita.mapper;

import com.mita.dto.ElementMetaDataDTO;
import com.mita.dto.StepResultScreenshotComparisonDTO;
import com.mita.model.ElementMetaData;
import com.mita.model.StepResultScreenshotComparison;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface StepResultScreenshotComparisonMapper {

  ElementMetaDataDTO mapMetaData(ElementMetaData elementMetaData);

  StepResultScreenshotComparisonDTO map(StepResultScreenshotComparison screenshotComparison);

  List<StepResultScreenshotComparisonDTO> map(List<StepResultScreenshotComparison> screenshotComparisons);
}
