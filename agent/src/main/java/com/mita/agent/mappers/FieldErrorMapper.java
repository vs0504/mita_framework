

package com.mita.agent.mappers;

import com.mita.agent.dto.FieldErrorDTO;
import com.mita.agent.dto.ObjectErrorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FieldErrorMapper {
  List<FieldErrorDTO> map(List<FieldError> fieldError);

  List<ObjectErrorDTO> mapObjectErrors(List<ObjectError> objectErrors);
}
