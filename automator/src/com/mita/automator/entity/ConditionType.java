

package com.mita.automator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConditionType {
  NOT_USED, CONDITION_IF, CONDITION_IF_NOT, CONDITION_ELSE_IF, CONDITION_ELSE_IF_NOT, CONDITION_ELSE, LOOP_FOR, LOOP_WHILE
}
