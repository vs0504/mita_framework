

package com.mita.agent.controllers;

import com.mita.automator.http.HttpResponse;
import com.mita.agent.config.AgentConfig;
import com.mita.agent.constants.AgentOs;
import com.mita.agent.dto.AgentDTO;
import com.mita.agent.http.ServerURLBuilder;
import com.mita.agent.http.WebAppHttpClient;
import com.mita.agent.mobile.android.AndroidDeviceListener;
import com.mita.agent.mobile.ios.IosDeviceListener;
import com.mita.agent.schedulers.BaseScheduler;
import com.mita.agent.services.AgentBrowserService;
import com.mita.agent.services.AgentService;
import com.mita.agent.ws.server.AgentWebServer;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomeController {
  private final AgentBrowserService agentBrowserService;
  private final AndroidDeviceListener androidDeviceListener;
  private final IosDeviceListener iosDeviceListener;
  private final AgentConfig agentConfig;
  private final WebAppHttpClient httpClient;
  private final AgentWebServer agentWebServer;

  @PutMapping(value = "/{uuid}/register", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> register(@PathVariable("uuid") String uuid,
                                         @RequestParam(value = "jwtApiKey", required = false) String jwtApiKey) {
    ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.ACCEPTED);
    try {
      log.debug("Received sync request for agent with uuid - " + uuid);
      String hostName = AgentService.getComputerName();
      AgentOs osType = AgentOs.getLocalAgentOs();


      AgentDTO agentDTO = new AgentDTO();
      agentDTO.setHostName(hostName);
      agentDTO.setOsVersion(AgentService.getOsVersion());
      agentDTO.setAgentVersion(this.agentConfig.getAgentVersion());
      agentDTO.setBrowserList(agentBrowserService.getBrowserList());
      agentDTO.setHostName(hostName);
      agentDTO.setOsType(osType);

      String authHeader = WebAppHttpClient.BEARER + " " + jwtApiKey;
      HttpResponse<AgentDTO> syncResponse = httpClient.put(ServerURLBuilder.agentURL(uuid),
        agentDTO, new TypeReference<>() {
        }, authHeader);

      if (syncResponse.getStatusCode() == HttpStatus.OK.value()) {
        agentConfig.setJwtApiKey(jwtApiKey);
        agentConfig.setUUID(uuid);
        agentConfig.setRegistered("true");
        agentConfig.saveConfig();
        BaseScheduler.setSkip(Boolean.FALSE);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(androidDeviceListener);
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        executorService1.submit(iosDeviceListener);
      } else {
        response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
    }
    return response;
  }
}
