

package com.mita.controller.api.agent;

import com.mita.exception.ResourceNotFoundException;
import com.mita.exception.MitaException;
import com.mita.mapper.TestCaseResultMapper;
import com.mita.service.TestCaseResultService;
import com.mita.web.request.TestCaseResultRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController(value = "agentTestCaseResultsController")
@RequestMapping(path = "/api/agents/test_case_results/{id}")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
@CrossOrigin
public class TestCaseResultsController {

  private final TestCaseResultService testCaseResultService;
  private final TestCaseResultMapper testCaseResultMapper;

  @RequestMapping(method = RequestMethod.PUT, consumes = "application/json;charset=UTF-8")
  public ResponseEntity<String> update(@PathVariable("id") Long id,
                                       @RequestBody TestCaseResultRequest testCaseResultRequest)
    throws UnsupportedEncodingException, MitaException {
    testCaseResultService.updateResult(testCaseResultRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(path = "/result", method = RequestMethod.PUT)
  public ResponseEntity<String> updateResult(@PathVariable("id") Long id,
                                             @RequestBody TestCaseResultRequest testCaseResultRequest)
    throws ResourceNotFoundException {
    testCaseResultService.updateResultData(testCaseResultRequest);
    return new ResponseEntity<String>(HttpStatus.OK);
  }
}
