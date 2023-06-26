

package com.mita.automator.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum WorkspaceType {
  WebApplication,
  MobileWeb,
  AndroidNative,
  IOSNative,
  Rest;

  public static boolean isMobileApp(WorkspaceType appType) {
    return (WorkspaceType.MobileWeb.equals(appType) ||
      WorkspaceType.AndroidNative.equals(appType) ||
      WorkspaceType.IOSNative.equals(appType));
  }

  public static boolean isIOSNative(WorkspaceType workspaceType) {
    return workspaceType == IOSNative;
  }
}
