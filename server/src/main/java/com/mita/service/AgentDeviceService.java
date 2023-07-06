

package com.mita.service;

import com.mita.exception.ResourceNotFoundException;
import com.mita.exception.MitaDatabaseException;
import com.mita.repository.AgentDeviceRepository;
import com.mita.dto.AgentDeviceDTO;
import com.mita.event.AgentDeviceEvent;
import com.mita.event.EventType;
import com.mita.model.AgentDevice;
import com.mita.model.ProvisioningProfileDevice;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
public class AgentDeviceService {

  private final AgentDeviceRepository agentDeviceRepository;
  private final ProvisioningProfileDeviceService profileDeviceService;
  private final ApplicationEventPublisher applicationEventPublisher;

  public Page<AgentDevice> findAllByAgentId(Long agentId, Pageable pageable) {
    return agentDeviceRepository.findAllByAgentId(agentId, pageable);
  }

  public List<AgentDevice> findAllByAgent(Long agentId) {
    return agentDeviceRepository.findAllByAgentId(agentId);
  }

  public Page<AgentDevice> findAllByAgentIdAndIsOnline(Long agentId, Pageable pageable) {
    return agentDeviceRepository.findAllByAgentIdAndIsOnline(agentId, true, pageable);
  }

  public AgentDevice create(AgentDevice agentDevice) throws MitaDatabaseException {
    try {
      agentDevice = agentDeviceRepository.save(agentDevice);
      profileDeviceService.updateAgentDevice(agentDevice);
      publishEvent(agentDevice, EventType.CREATE);
      return agentDevice;
    } catch (Exception e) {
      throw new MitaDatabaseException(e.getMessage());
    }
  }

  public AgentDevice update(AgentDevice agentDevice) throws MitaDatabaseException {
    try {
      agentDevice = agentDeviceRepository.save(agentDevice);
      publishEvent(agentDevice, EventType.UPDATE);
      return agentDevice;
    } catch (Exception e) {
      throw new MitaDatabaseException(e.getMessage());
    }
  }

  public void destroy(AgentDevice agentDevice) throws MitaDatabaseException {
    try {
      agentDeviceRepository.delete(agentDevice);
      publishEvent(agentDevice, EventType.DELETE);
    } catch (Exception e) {
      throw new MitaDatabaseException(e.getMessage());
    }
  }

  public AgentDevice findAgentDeviceByUniqueId(Long agentId, String uniqueId) throws ResourceNotFoundException {
    return agentDeviceRepository.findAgentDeviceByAgentIdAndUniqueId(agentId, uniqueId).orElseThrow(() -> new ResourceNotFoundException(
      "Device not found with uniqueId " + uniqueId + " associated to agent " + agentId
    ));
  }

  public void updateDevicesStatus(Long agentId) throws MitaDatabaseException {
    try {
      agentDeviceRepository.updateAgentDevice(agentId);
    } catch (Exception e) {
      throw new MitaDatabaseException(e.getMessage());
    }
  }

  public AgentDevice find(Long id) throws ResourceNotFoundException {
    return agentDeviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("AgentDevice is not " +
      "found with id:" + id));
  }

  public List<AgentDevice> findByUniqueId(String deviceUDID) throws ResourceNotFoundException {
    return this.agentDeviceRepository.findAllByUniqueId(deviceUDID);
  }

  public void setProvisionedFlag(List<AgentDeviceDTO> agentDeviceDTOs) {
    for (AgentDeviceDTO agentDeviceDTO : agentDeviceDTOs) {
      if(agentDeviceDTO.getIsEmulator()) {
        agentDeviceDTO.setProvisioned(true);
        continue;
      }
      ProvisioningProfileDevice profileDevice = profileDeviceService.findByAgentDeviceId(agentDeviceDTO.getId());
      agentDeviceDTO.setProvisioned(profileDevice != null);
    }
  }

  public void publishEvent(AgentDevice agentDevice, EventType eventType) {
    AgentDeviceEvent<AgentDevice> event = createEvent(agentDevice, eventType);
    log.info("Publishing event - " + event.toString());
    applicationEventPublisher.publishEvent(event);
  }

  public AgentDeviceEvent<AgentDevice> createEvent(AgentDevice agentDevice, EventType eventType) {
    AgentDeviceEvent<AgentDevice> event = new AgentDeviceEvent<>();
    event.setEventData(agentDevice);
    event.setEventType(eventType);
    return event;
  }

  public boolean isDeviceOnline(Long deviceId) throws ResourceNotFoundException{
    AgentDevice agentDevice = find(deviceId);
    return agentDevice.getIsOnline();
  }
}

