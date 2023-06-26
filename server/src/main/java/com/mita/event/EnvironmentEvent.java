package com.mita.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EnvironmentEvent<T> extends BaseEvent<T> {
  public String toString() {
    return super.toString();
  }
}