package com.mita.agent.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DeviceStatus {
    BOOTED("Booted"),
    SHUTDOWN("Shutdown");

    @Getter
    private final String status;
}
