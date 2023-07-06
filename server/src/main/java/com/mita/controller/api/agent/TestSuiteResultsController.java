

package com.mita.controller.api.agent;

import com.mita.exception.ResourceNotFoundException;
import com.mita.exception.MitaDatabaseException;
import com.mita.exception.MitaException;
import com.mita.mapper.TestSuiteResultMapper;
import com.mita.service.TestSuiteResultService;
import com.mita.web.request.TestSuiteResultRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "agentTestSuiteResultsController")
@RequestMapping(path = "/api/agents/test_suite_results/{id}")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@CrossOrigin
public class TestSuiteResultsController {

  private final TestSuiteResultService testSuiteResultService;
  private final TestSuiteResultMapper testSuiteResultMapper;

  @RequestMapping(method = RequestMethod.PUT)
  public ResponseEntity<String> update(@PathVariable("id") Long id,
                                       @RequestBody TestSuiteResultRequest testSuiteResultRequest)
    throws MitaException {
    testSuiteResultService.updateResult(testSuiteResultRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(path = "/result", method = RequestMethod.PUT)
  public ResponseEntity<String> updateResult(@PathVariable("id") Long id,
                                             @RequestBody TestSuiteResultRequest testCaseGroupResultRequest)
    throws MitaDatabaseException, ResourceNotFoundException {
    testSuiteResultService.updateResultData(testCaseGroupResultRequest);
    return new ResponseEntity<String>(HttpStatus.OK);
  }
}
