

package com.mita.model;

import lombok.Data;

import java.util.List;

@Data
public class PlatformOsDevice {
  private String osName;
  private String osDisplayName;
  private List<PlatformDevice> platformDevices;
}
