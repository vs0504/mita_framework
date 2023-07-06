

package com.mita.agent.mobile.ios;


import com.mita.agent.exception.DeviceContainerException;
import com.mita.agent.exception.MitaException;
import com.mita.agent.mobile.ios.libs.LibIMobileDevice;
import com.mita.agent.mobile.MobileDevice;
import com.sun.jna.Pointer;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DeviceEventCallback implements LibIMobileDevice.idevice_event_cb_t {

  private final IosDeviceListener iosDeviceListener;

  DeviceEventCallback(IosDeviceListener iosDeviceListener) {
    this.iosDeviceListener = iosDeviceListener;
  }

  @Override
  public void apply(LibIMobileDevice.IdeviceEvent iDeviceEvent, Pointer user_data) {
    try {
      int event = iDeviceEvent.event;
      String uuid = iDeviceEvent.uuid.getString(0);

      switch (event) {
        case 1:
          onDeviceAdded(uuid);
          break;
        case 2:
          onDeviceRemoved(uuid);
          break;
        case 3:
          onDevicePaired(uuid);
          break;
        default:
          throw new MitaException("event type " + event + "not recognized.");
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  public void onDeviceAdded(String uuid) throws MitaException, DeviceContainerException {
    MobileDevice mobileDevice = iosDeviceListener.getRealMobileDevice(uuid);
    mobileDevice.setIsOnline(true);
    mobileDevice.setIsEmulator(false);
    iosDeviceListener.addDevice(mobileDevice);
  }

  public void onDeviceRemoved(String uuid) throws MitaException, DeviceContainerException {
    MobileDevice mobileDevice = iosDeviceListener.getRealMobileDevice(uuid);
    mobileDevice.setIsOnline(false);
    mobileDevice.setIsEmulator(false);
    iosDeviceListener.removeDevice(mobileDevice);
    System.out.println("Removed: " + uuid);
  }

  public void onDevicePaired(String uuid) throws MitaException, DeviceContainerException {
    MobileDevice mobileDevice = iosDeviceListener.getRealMobileDevice(uuid);
    mobileDevice.setIsOnline(true);
    mobileDevice.setIsEmulator(false);
    iosDeviceListener.updateDevice(mobileDevice);
    System.out.println("Paired: " + uuid);
  }
}
