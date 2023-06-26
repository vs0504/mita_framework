

package com.mita.agent.mobile.ios;

import com.mita.agent.constants.MobileOs;
import com.mita.agent.exception.DeviceContainerException;
import com.mita.agent.exception.TestsigmaException;
import com.mita.agent.mappers.MobileDeviceMapper;
import com.mita.agent.mobile.MobileDevice;
import com.mita.agent.mobile.android.AdbBridge;
import com.mita.agent.mobile.android.CommandExecutor;
import com.mita.agent.services.DriverSessionsService;
import com.mita.agent.config.AgentConfig;
import com.mita.agent.http.WebAppHttpClient;
import com.mita.agent.mobile.DeviceContainer;
import com.mita.agent.mobile.DeviceListener;
import com.mita.agent.mobile.SessionContainer;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
@Component
public class IosDeviceListener extends DeviceListener {

  private final IosDeviceListenerTask iosDeviceListenerTask;
  private final ExecutorService executorService;
  private boolean isStarted = false;
  private UsbMuxSocket usbMuxSocket;
  private String registerUid;

  public IosDeviceListener(
          MobileDeviceMapper mobileDeviceMapper,
          WebAppHttpClient httpClient,
          DeviceContainer deviceContainer,
          AgentConfig agentConfig,
          AdbBridge adbBridge,
          CommandExecutor commandExecutor,
          SessionContainer sessionContainer,
          DriverSessionsService driverSessionsService,
          IosDeviceService iosDeviceService,
          DeveloperImageService developerImageService
  ) {
    super(mobileDeviceMapper, httpClient, deviceContainer, agentConfig,
      adbBridge, commandExecutor, sessionContainer, driverSessionsService, iosDeviceService, developerImageService);
    this.listenerType = "IOS";
    this.iosDeviceListenerTask = new IosDeviceListenerTask();
    this.executorService = Executors.newSingleThreadExecutor();
  }

  public void initializeNativeBridge() throws TestsigmaException {
    if (bridgeInitialized) {
      return;
    }
    try {
      this.usbMuxSocket = iosDeviceService.createConnection();
      bridgeInitialized = true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new TestsigmaException(e.getMessage(), e.getMessage());
    }
  }

  public void getInitialDeviceList() throws TestsigmaException, DeviceContainerException {
    getInitialSimulatorDevices();
    List<Device> devices = iosDeviceService.deviceList();
    for (Device device : devices) {
      MobileDevice mobileDevice = getRealMobileDevice(device.getSerialNumber());
      mobileDevice.setMuxDeviceId(device.getDeviceId().toString());
      this.addDevice(mobileDevice);
    }
  }

  @Override
  public void addDeviceListenerCallback() {
    log.info("Starting iOS Device Listener");
    try {
      this.registerUid = iosDeviceListenerTask.register(m -> {
        switch (m.type) {
          case Add:
            try {
              log.info("Device connected - " + m.device);
              MobileDevice device = getRealMobileDevice(m.device.getSerialNumber());
              device.setMuxDeviceId(m.device.getDeviceId().toString());
              this.addDevice(device);
              this.developerImageService.mountDeveloperImage(device);
            } catch (Exception e) {
              log.error(e.getMessage(), e);
            }
            break;
          case Remove:
            try {
              log.info("Device disconnected - " + m.device);
              MobileDevice device = this.deviceContainer.getDeviceByMuxId(m.device.getDeviceId().toString());
              this.removeDevice(device);
              break;
            } catch (Exception e) {
              log.error(e.getMessage(), e);
            }
        }
      });
      iosDeviceListenerTask.start(usbMuxSocket.getSocket().getInputStream());
      isStarted = true;
      Map<String, Object> payload = new HashMap<>();
      payload.put("MessageType", "Listen");
      usbMuxSocket.sendPacket(payload);
      executorService.execute(iosDeviceListenerTask);
      log.info("Successfully started device listener task");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  @PreDestroy
  public void removeDeviceListenerCallback() {
    log.info("Stopping iOS Device Listener");
    if (isStarted) {
      iosDeviceListenerTask.stop();
      iosDeviceListenerTask.unregister(registerUid);
      try {
        iosDeviceService.closeConnection(this.usbMuxSocket);
      } catch (Exception e) {
        usbMuxSocket = null;
      }
      isStarted = false;
    }
  }

  public void getInitialSimulatorDevices() {
    try {
      List<MobileDevice> devices = iosDeviceService.simulatorDeviceList();
      for (MobileDevice device : devices) {
        log.info("Adding device {} to device container", device);
        this.addDevice(device);
      }
    } catch(Exception e) {
      log.error("Error in fetching Simulator devices",e);
    }
  }

  public MobileDevice getRealMobileDevice(String uniqueId) throws TestsigmaException {
    MobileDevice mobileDevice = new MobileDevice();
    mobileDevice.setOsName(MobileOs.IOS);
    mobileDevice.setUniqueId(uniqueId);
    JSONObject deviceProperties = iosDeviceService.getDeviceProperties(uniqueId);
    mobileDevice.setName(deviceProperties.getString("DeviceName"));
    mobileDevice.setOsVersion(deviceProperties.getString("ProductVersion"));
    mobileDevice.setApiLevel(mobileDevice.getOsVersion());
    mobileDevice.setAbi(deviceProperties.getString("CPUArchitecture"));
    mobileDevice.setProductModel(deviceProperties.getString("ProductType"));
    mobileDevice.setScreenWidth(deviceProperties.getInt("ScreenWidth"));
    mobileDevice.setScreenHeight(deviceProperties.getInt("ScreenHeight"));
    mobileDevice.setIsOnline(true);
    mobileDevice.setIsEmulator(false);
    return mobileDevice;
  }

}

