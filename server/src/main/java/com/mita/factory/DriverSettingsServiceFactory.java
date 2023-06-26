package com.mita.factory;

import com.mita.service.DriverSettingsService;
import com.mita.service.HybridDriverSettingsService;
import com.mita.service.PrivateGridDriverSettingsService;
import com.mita.service.TestsigmaLabDriverSettingsService;
import com.mita.model.TestPlanLabType;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.context.WebApplicationContext;

@Log4j2
public class DriverSettingsServiceFactory {
  private final WebApplicationContext context;

  public DriverSettingsServiceFactory(WebApplicationContext context) {
    this.context = context;
  }

  public DriverSettingsService driverSettingsService(TestPlanLabType testPlanLabType) {
    TestsigmaLabDriverSettingsService testsigmaLabDriverSettingsService = (TestsigmaLabDriverSettingsService) context.getBean("testsigmaLabDriverSettingsService");
    HybridDriverSettingsService hybridDriverSettingsService = (HybridDriverSettingsService) context.getBean("hybridDriverSettingsService");
    PrivateGridDriverSettingsService privateGridDriverSettingsService = (PrivateGridDriverSettingsService) context.getBean("privateGridDriverSettingsService");
    switch (testPlanLabType) {
      case TestsigmaLab:
        return testsigmaLabDriverSettingsService;
      case Hybrid:
        return hybridDriverSettingsService;
      case PrivateGrid:
        return privateGridDriverSettingsService;
    }
    return null;
  }
}
