

package com.mita.automator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultConstant {
  SUCCESS(0),
  FAILURE(1),
  ABORTED(2),
  NOT_EXECUTED(3),
  QUEUED(4),
  STOPPED(5);

  private final Integer id;
}
