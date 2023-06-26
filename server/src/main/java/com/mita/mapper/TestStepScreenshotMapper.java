

package com.mita.mapper;

import com.mita.dto.TestStepScreenshotDTO;
import com.mita.model.TestStepScreenshot;
import com.mita.web.request.TestStepScreenshotRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TestStepScreenshotMapper {

  void merge(TestStepScreenshotRequest request, @MappingTarget TestStepScreenshot stepScreenshot);

  TestStepScreenshotDTO map(TestStepScreenshot testStepScreenshot);

}
