

package com.mita.controller;

import com.mita.specification.ScreenshotComparisionSpecificationsBuilder;
import com.mita.config.StorageServiceFactory;
import com.mita.model.StorageAccessLevel;
import com.mita.dto.StepResultScreenshotComparisonDTO;
import com.mita.exception.ResourceNotFoundException;
import com.mita.mapper.StepResultScreenshotComparisonMapper;
import com.mita.model.StepResultScreenshotComparison;
import com.mita.model.TestStepResult;
import com.mita.model.TestStepScreenshot;
import com.mita.service.StepResultScreenshotComparisonService;
import com.mita.service.TestStepScreenshotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;

@RestController
@RequestMapping(path = "/screenshot_comparisons", produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@CrossOrigin
public class ScreenshotComparisonsController {
  private final TestStepScreenshotService testStepScreenshotService;
  private final StepResultScreenshotComparisonService service;
  private final StepResultScreenshotComparisonMapper mapper;
  private final StorageServiceFactory storageServiceFactory;

  @GetMapping
  public Page<StepResultScreenshotComparisonDTO> index(ScreenshotComparisionSpecificationsBuilder builder, Pageable pageable) {
    log.info("Request /screenshot_comparisons/");
    Specification<StepResultScreenshotComparison> spec = builder.build();
    Page<StepResultScreenshotComparison> comparisons = service.findAll(spec, pageable);
    List<StepResultScreenshotComparisonDTO> testStepResultDTOS =
      mapper.map(comparisons.getContent());
    return new PageImpl<>(testStepResultDTOS, pageable, comparisons.getTotalElements());
  }

  @GetMapping("/{id}")
  public StepResultScreenshotComparisonDTO show(@PathVariable(value = "id") Long id) throws Exception {
    log.info("Request /screenshot_comparisons/" + id);
    StepResultScreenshotComparison comparison = service.find(id);
    TestStepResult testStepResult = comparison.getTestStepResult();
    Long screenShotPathId = testStepResult.getTestCaseResultId();
    String currentScreenShotPath =
      "/executions/" + screenShotPathId + "/" + testStepResult.getScreenshotName();
    URL url = storageServiceFactory.getStorageService().generatePreSignedURL(currentScreenShotPath, StorageAccessLevel.READ);
    comparison.setScreenShotURL(url.toString());

    TestStepResult baseTestStepResult = comparison.getTestStepScreenshot().getTestStepResult();
    Long baseScreenShotPathId = baseTestStepResult.getTestCaseResultId();
    String baseScreenShotPath =
      "/executions/" + baseScreenShotPathId + "/" + baseTestStepResult.getScreenshotName();
    url = storageServiceFactory.getStorageService().generatePreSignedURL(baseScreenShotPath, StorageAccessLevel.READ);
    comparison.getTestStepScreenshot().setScreenShotURL(url.toString());
    return mapper.map(comparison);
  }

  @PutMapping("/{id}/mark_as_base")
  public StepResultScreenshotComparisonDTO markAsBaseLine(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
    log.info("Request /screenshot_comparisons/" + id);
    StepResultScreenshotComparison comparison = service.find(id);
    TestStepResult testStepResult = comparison.getTestStepResult();
    TestStepScreenshot testStepScreenshot = comparison.getTestStepScreenshot();
    testStepScreenshot.setTestStepResultId(testStepResult.getId());
    testStepScreenshot.setEnvironmentResultId(testStepResult.getEnvRunId());
    testStepScreenshot.setTestCaseResultId(testStepResult.getTestCaseResultId());
    testStepScreenshot.setBaseImageName(testStepResult.getScreenshotName());
    testStepScreenshotService.update(testStepScreenshot);
    comparison.setDiffCoordinates("[]");
    comparison = service.update(comparison);
    service.propagateVisualResult(comparison);
    return mapper.map(comparison);
  }
}
