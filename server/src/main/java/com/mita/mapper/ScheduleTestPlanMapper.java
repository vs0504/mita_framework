

package com.mita.mapper;

import com.mita.dto.ScheduleTestPlanDTO;
import com.mita.model.ScheduleTestPlan;
import com.mita.web.request.ScheduleTestPlanRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ScheduleTestPlanMapper {

  ScheduleTestPlan map(ScheduleTestPlanRequest request);

  @Mapping(target = "scheduleTime", expression = "java(new java.text.SimpleDateFormat(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(scheduleTestPlan.getScheduleTime()))")
  ScheduleTestPlanDTO mapToDTO(ScheduleTestPlan scheduleTestPlan);

  List<ScheduleTestPlanDTO> mapToDTO(List<ScheduleTestPlan> attachments);

  void merge(ScheduleTestPlanRequest request, @MappingTarget ScheduleTestPlan scheduleTestPlan);
}
