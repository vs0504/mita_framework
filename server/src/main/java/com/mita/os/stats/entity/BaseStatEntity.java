package com.mita.os.stats.entity;

import com.mita.os.stats.event.EventType;
import lombok.Data;

@Data
public abstract class BaseStatEntity {
  private String serverUuid;
  private EventType eventType;
}
