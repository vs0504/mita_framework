

package com.mita.agent.mobile;

import com.mita.agent.exception.DeviceContainerException;
import com.mita.agent.exception.NativeBridgeException;
import com.mita.agent.exception.MitaException;
import com.mita.agent.mappers.MobileDeviceMapper;
import com.mita.agent.mobile.android.AdbBridge;
import com.mita.agent.mobile.android.CommandExecutor;
import com.mita.agent.mobile.ios.DeveloperImageService;
import com.mita.agent.mobile.ios.IosDeviceService;
import com.mita.agent.services.DriverSessionsService;
import com.mita.agent.config.AgentConfig;
import com.mita.agent.http.ServerURLBuilder;
import com.mita.agent.http.WebAppHttpClient;
import com.mita.automator.exceptions.AutomatorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public abstract class DeviceListener implements Runnable {
  protected final MobileDeviceMapper mobileDeviceMapper;
  protected final WebAppHttpClient httpClient;
  protected final DeviceContainer deviceContainer;
  protected final AgentConfig agentConfig;
  protected final AdbBridge adbBridge;
  protected final CommandExecutor commandExecutor;
  protected final SessionContainer sessionContainer;
  protected final DriverSessionsService driverSessionsService;
  protected final IosDeviceService iosDeviceService;
  protected final DeveloperImageService developerImageService;

  protected Boolean bridgeInitialized = false;
  protected String listenerType;

  public void run() {
    log.debug("Device listener triggered for " + listenerType + " devices");
    if (!shouldListen()) {
      return;
    }

    try {
      initializeNativeBridge();
      getInitialDeviceList();
      addDeviceListenerCallback();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  public void addDevice(MobileDevice device) throws DeviceContainerException {
    if (!bridgeInitialized) {
      log.info("Native bridge is not yet initialized");
      return;
    }
    if (!device.getIsOnline()) {
      log.info("Device is offline. Skipping the device from container.");
      return;
    }
    deviceContainer.addDevice(device);
  }

  public void removeDevice(MobileDevice device) throws DeviceContainerException {
    try {
      driverSessionsService.disconnectDeviceSession(device.getUniqueId());
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    deviceContainer.deleteDevice(device.getUniqueId());
  }

  public void updateDevice(MobileDevice device) throws DeviceContainerException {
    this.addDevice(device);
  }

  public abstract void getInitialDeviceList() throws MitaException, DeviceContainerException, AutomatorException;

  public abstract void initializeNativeBridge() throws MitaException, NativeBridgeException;

  public abstract void addDeviceListenerCallback() throws MitaException;

  public boolean shouldListen() {
    boolean listen = true;
    if (agentConfig.getRegistered().equals(Boolean.FALSE)) {
      log.debug("Agent is not yet registered...skipping device listener...");
      listen = false;
    }

    return listen;
  }

  public void syncInitialDeviceStatus() {
    try {
      if (shouldListen()) {
        String agentUuid = agentConfig.getUUID();
        String authHeader = WebAppHttpClient.BEARER + " " + this.agentConfig.getJwtApiKey();
        httpClient.put(ServerURLBuilder.agentDeviceStatusURL(agentUuid), "", null, authHeader);
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }
}
