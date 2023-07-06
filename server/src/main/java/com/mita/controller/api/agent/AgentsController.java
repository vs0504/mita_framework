

package com.mita.controller.api.agent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mita.config.URLConstants;
import com.mita.dto.AgentDTO;
import com.mita.dto.AgentExecutionDTO;
import com.mita.dto.AgentWebServerConfigDTO;
import com.mita.dto.EnvironmentEntityDTO;
import com.mita.exception.ResourceNotFoundException;
import com.mita.exception.MitaException;
import com.mita.mapper.AgentMapper;
import com.mita.model.*;
import com.mita.service.AgentService;
import com.mita.service.PlatformsService;
import com.mita.service.TestDeviceResultService;
import com.mita.service.TestsigmaOSConfigService;
import com.mita.util.HttpClient;
import com.mita.util.HttpResponse;
import com.mita.web.request.AgentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController(value = "agentAgentsController")
@RequestMapping(path = "/api/agents")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@CrossOrigin
public class AgentsController {
  private final AgentService agentService;
  private final AgentMapper mapper;
  private final TestDeviceResultService testDeviceResultService;
  private final PlatformsService platformService;
  private final HttpClient httpClient;
  private final TestsigmaOSConfigService testsigmaOSConfigService;

  @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
  public AgentDTO show(@PathVariable("uuid") String uniqueId)
    throws ResourceNotFoundException {
    Agent agent = agentService.findByUniqueId(uniqueId);
    return mapper.map(agent);
  }

  @RequestMapping(path = "/{uuid}", method = RequestMethod.PUT)
  public AgentDTO update(@RequestBody AgentRequest agentRequest, @PathVariable("uuid") String uniqueId)
    throws ResourceNotFoundException {
    log.info("Request /api/agents/" + uniqueId + " received with data: " + agentRequest.toString());
    Agent agent = agentService.update(agentRequest, uniqueId);
    return mapper.map(agent);
  }

  @RequestMapping(path = "/{uuid}/execution", method = RequestMethod.GET)
  public AgentExecutionDTO getExecution(HttpServletResponse response, @PathVariable("uuid") String uniqueId)
    throws Exception {
    log.info("Request /api/agents/" + uniqueId + "/test_plans received");

    EnvironmentEntityDTO environmentEntityDTO = null;

    Agent agent = agentService.findByUniqueId(uniqueId);
    TestDeviceResult testDeviceResult =
      testDeviceResultService.findQueuedHybridEnvironment(agent.getId());
    if (testDeviceResult != null) {
      List<EnvironmentEntityDTO> environmentEntityDTOs = testDeviceResultService.getHybridEnvironmentEntitiesInPreFlight(new ArrayList<>() {{
        add(testDeviceResult);
      }});
      if (environmentEntityDTOs.size() > 0) {
        environmentEntityDTO = environmentEntityDTOs.get(0);
        environmentEntityDTO = environmentEntityDTO.getTestSuites() != null && environmentEntityDTO.getTestSuites().size() == 0 ? null : environmentEntityDTO;
      }
    }
    AgentExecutionDTO executionDTO = new AgentExecutionDTO();
    executionDTO.setEnvironment(environmentEntityDTO);
    response.setHeader("X-Request-Id", ThreadContext.get("X-Request-Id"));

    return executionDTO;
  }


  @RequestMapping(path = "/certificate", method = RequestMethod.GET)
  public AgentWebServerConfigDTO getWebServerCertificate() throws MitaException {
    HttpResponse<AgentWebServerConfigDTO> response = httpClient.get(testsigmaOSConfigService.getUrl() +
      URLConstants.TESTSIGMA_OS_PUBLIC_CERTIFICATE_URL, getHeaders(), new TypeReference<>() {
    });
    return response.getResponseEntity();
  }

  @RequestMapping(path = "/{uuid}/driver/executable_path", method = RequestMethod.GET)
  public String getExecutablePath(@PathVariable("uuid") String uniqueId,
                                  @RequestParam("browserName") String browserName,
                                  @RequestParam("browserVersion") String browserVersion
  ) throws MitaException {
    log.info(String.format("Request received for get executable path for browser - %s | version - %s | uuid - %s",
      browserName, browserVersion, uniqueId));
    Agent agent = agentService.findByUniqueId(uniqueId);
    Browsers browser = Browsers.getBrowser(browserName);
    if (browser == null) {
      throw new MitaException("Browser - " + browserName + " is not supported");
    }

    PlatformBrowserVersion platformBrowserVersion =
      platformService.getPlatformBrowserVersion(agent.getOsType().getPlatform(),
        agent.getPlatformOsVersion(agent.getOsType().getPlatform()), browser,
        browserVersion, TestPlanLabType.Hybrid);

    if (platformBrowserVersion == null) {
      throw new MitaException("Cant find browser with details. Browser Name - " + browserName
        + ", Browser Version - " + browserVersion + ", Platform - " + agent.getOsType().getPlatform());
    }

    return platformService.getDriverPath(platformBrowserVersion.getPlatform(),
      platformBrowserVersion.getVersion(), platformBrowserVersion.getName(),
      platformBrowserVersion.getDriverVersion());
  }

  private ArrayList<Header> getHeaders() {
    ArrayList<Header> headers = new ArrayList<>();
    headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
    return headers;
  }
}
