

package com.mita.automator.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OnAbortedAction {
  Reuse_Session, Restart_Session
}
