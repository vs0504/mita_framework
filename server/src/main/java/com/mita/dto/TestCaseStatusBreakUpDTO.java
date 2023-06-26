package com.mita.dto;

import com.mita.model.TestCaseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestCaseStatusBreakUpDTO {
  TestCaseStatus status;
  Long count;
}
