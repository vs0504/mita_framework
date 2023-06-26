

package com.mita.controller.api.agent;

import com.mita.exception.ResourceNotFoundException;
import com.mita.service.TestDeviceResultService;
import com.mita.web.request.EnvironmentRunResultRequest;
import com.mita.web.request.TestDeviceResultRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "agentTestDeviceResultsController")
@RequestMapping(path = "/api/agents/test_device_results/{id}")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class TestDeviceResultsController {

  private final TestDeviceResultService testDeviceResultService;

  @RequestMapping(method = RequestMethod.PUT)
  public ResponseEntity<String> update(@PathVariable("id") Long environmentResultId,
                                       @RequestBody EnvironmentRunResultRequest environmentResultRequest)
    throws Exception {
    testDeviceResultService.updateResult(environmentResultRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(path = "/result", method = RequestMethod.PUT)
  public ResponseEntity<String> updateResult(@PathVariable("id") Long environmentResultId,
                                             @RequestBody TestDeviceResultRequest testDeviceResultRequest)
    throws ResourceNotFoundException {
    testDeviceResultService.updateResultData(testDeviceResultRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
