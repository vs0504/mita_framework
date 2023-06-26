package com.mita.agent.services;

import com.mita.automator.http.HttpResponse;
import com.mita.agent.config.AgentConfig;
import com.mita.agent.dto.AgentWebServerConfigDTO;
import com.mita.agent.http.ServerURLBuilder;
import com.mita.agent.http.WebAppHttpClient;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AgentWebServerService {
  private final WebAppHttpClient httpClient;
  private final AgentConfig agentConfig;

  public AgentWebServerConfigDTO getWebServerCertificate() throws IOException {
    HttpResponse<AgentWebServerConfigDTO> response = httpClient.get(ServerURLBuilder.webServerCertificateFetchURL(),
      new TypeReference<>() {
      });
    if (response.getStatusCode() == HttpStatus.OK.value()) {
      return response.getResponseEntity();
    } else {
      log.info("Could not fetch agent web server config from testsigma servers. Response code - "
        + response.getStatusCode() + " , message - " + response.getResponseText());
    }
    return null;
  }

  public void registerLocalAgent() {
    try {
      if (!agentConfig.getRegistered() && agentConfig.getLocalAgentRegister()) {
        log.info("Triggering local agent registration since agent is not registered");
        agentConfig.setUUID(UUID.randomUUID().toString());
        agentConfig.saveConfig();

        HttpResponse<String> response = httpClient.get(ServerURLBuilder.registerLocalAgentURL(
          AgentService.getComputerName(), agentConfig.getLocalServerUrl()), new TypeReference<>() {
        });
        if (response.getStatusCode() == HttpStatus.OK.value()) {
          log.info("Agent register triggered successfully");
        } else {
          log.info("Failed to trigger local agent registration. May be server is not running in localhost. " +
            "Response code - " + response.getStatusCode() + " , message - " + response.getResponseText());
        }
      } else {
        log.info("Agent already registered...skipping local agent registration");
      }
    } catch (Exception e) {
      log.error("Failed to auto register local agent - " + e.getMessage(), e);
    }
  }

}
