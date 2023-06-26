

package com.mita.agent.mobile.ios.libs;

import com.sun.jna.Library;

public interface LibUsbMuxd extends Library {
  String MAC_LIBRARY_NAME = "libusbmuxd.6";
  String WIN_LIBRARY_NAME = "usbmuxd";
  String LINUX_LIBRARY_NAME = "libusbmuxd-2.0";
}
