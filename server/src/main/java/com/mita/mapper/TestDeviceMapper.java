

package com.mita.mapper;

import com.mita.dto.TestDeviceDTO;
import com.mita.model.TestDevice;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TestDeviceMapper {

  TestDeviceDTO map(TestDevice testDevice);

  List<TestDeviceDTO> map(List<TestDevice> testDevice);
}
