package com.mita.dto;

import lombok.Data;

import java.util.List;

@Data
public class TestDataProfileEntityDTO {

  private String testDataName;
  private String testData;
  private List<TestDataSetDTO> data;
}
