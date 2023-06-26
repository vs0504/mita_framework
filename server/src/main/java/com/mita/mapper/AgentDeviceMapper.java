package com.mita.mapper;

import com.mita.dto.AgentDeviceDTO;
import com.mita.model.AgentDevice;
import com.mita.web.request.AgentDeviceRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AgentDeviceMapper {
  @Mapping(target = "browserList", expression = "java(agentDeviceRequest.getAgentBrowserList())")
  void map(AgentDeviceRequest agentDeviceRequest, @MappingTarget AgentDevice agentDevice);

  @Mapping(target = "browserList", expression = "java(agentDeviceRequest.getAgentBrowserList())")
  AgentDevice map(AgentDeviceRequest agentDeviceRequest);

  AgentDeviceDTO map(AgentDevice agentDevice);

  List<AgentDeviceDTO> map(List<AgentDevice> agentDevices);

}
