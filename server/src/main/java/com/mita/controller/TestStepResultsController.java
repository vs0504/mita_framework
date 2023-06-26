

package com.mita.controller;

import com.mita.specification.TestStepResultSpecificationsBuilder;
import com.mita.config.StorageServiceFactory;
import com.mita.model.StorageAccessLevel;
import com.mita.constants.MessageConstants;
import com.mita.dto.TestStepResultDTO;
import com.mita.mapper.TestStepResultMapper;
import com.mita.model.TestStepResult;
import com.mita.service.TestCaseResultService;
import com.mita.service.TestStepResultService;
import com.mita.web.request.TestStepResultRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.Calendar;
import java.util.List;

@RestController
@Log4j2
@RequestMapping(path = "/test_step_results")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class TestStepResultsController {

  private final TestStepResultService testStepResultService;
  private final TestStepResultMapper testStepResultMapper;
  private final StorageServiceFactory storageServiceFactory;
  private final TestCaseResultService testCaseResultService;


  @RequestMapping(method = RequestMethod.GET)
  public Page<TestStepResultDTO> index(TestStepResultSpecificationsBuilder builder, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
    log.info("Request /test_step_results/");
    Specification<TestStepResult> spec = builder.build();
    Page<TestStepResult> testStepResults = testStepResultService.findAll(spec, pageable);
    List<TestStepResultDTO> testStepResultDTOS =
      testStepResultMapper.mapDTO(testStepResults.getContent());
    return new PageImpl<>(testStepResultDTOS, pageable, testStepResults.getTotalElements());
  }

  @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
  public TestStepResultDTO show(@PathVariable(value = "id") Long id) throws Exception {
    log.info("Request /test_step_results/" + id);
    TestStepResult testStepResult = testStepResultService.find(id);
    if (testStepResult.getScreenshotName() != null) {
      String fileFullPath =
        "/executions/" + testStepResult.getTestCaseResultId() + "/" + testStepResult.getScreenshotName();
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.MINUTE, 10);
      URL preSignedURL = storageServiceFactory.getStorageService().generatePreSignedURL(fileFullPath, StorageAccessLevel.READ);
      log.info(String.format("Pre-signed URL for TestStepResultID %s is %s", id, preSignedURL));
      String screenShotSignedURL = preSignedURL != null ? preSignedURL.toString() : "";
      testStepResult.setScreenShotURL(screenShotSignedURL);
    }
    return testStepResultMapper.mapDTO(testStepResult);
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
  public TestStepResultDTO update(@PathVariable("id") Long id,
                                  @RequestBody TestStepResultRequest stepResultRequest)
    throws Exception {
    log.info("Request data /test_step_results/" + id + " : " + stepResultRequest.toString());
    TestStepResult stepResult = testStepResultService.find(id);
    stepResult.setResult(stepResultRequest.getResult());
    stepResult.setStartTime(stepResultRequest.getStartTime());
    stepResult.setEndTime(stepResultRequest.getEndTime());
    stepResult.setMessage(stepResultRequest.getMessage());
    stepResult.setDuration(stepResult.getEndTime().getTime() - stepResult.getStartTime().getTime());
    testStepResultService.update(stepResult);
    if (stepResult.getGroupResultId() != null) {
      testStepResultService.updateStepGroupResult(stepResult);
    } else {
      testStepResultService.updateTestStepResultUp(stepResult);
    }

    testStepResultService.updateStepGroupResult(stepResult.getResult(), MessageConstants.UPDATE_TEST_STEP_RESULT,
      stepResult.getStartTime(), stepResult.getEndTime(), id);
    testCaseResultService.updateResultCounts(testCaseResultService.find(stepResult.getTestCaseResultId()));
    return testStepResultMapper.mapDTO(stepResult);
  }

}
