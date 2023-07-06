package com.mita.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mita.exception.MitaException;
import com.mita.repository.ServerRepository;
import com.mita.config.ApplicationConfig;
import com.mita.config.URLConstants;
import com.mita.model.Server;
import com.mita.util.HttpClient;
import com.mita.util.HttpResponse;
import com.mita.web.request.ServerRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
public class ServerService {
  private final ServerRepository serverRepository;
  private final ApplicationConfig applicationConfig;
  private final HttpClient httpClient;
  private final TestsigmaOSConfigService testsigmaOSConfigService;
  private final Environment env;

  public Server findOne() throws MitaException {
    return this.serverRepository.findAll().stream().findFirst().orElseThrow(
      () -> new MitaException("Server Details Are Missing"));
  }

  private boolean shouldSync(Server server) {
    if (!server.getConsentRequestDone()) {
      boolean disableTelemetry = Boolean.parseBoolean(env.getProperty("DISABLE_TELEMETRY"));
      server.setConsent((disableTelemetry == Boolean.TRUE) ? Boolean.FALSE : Boolean.TRUE);
      update(server);
    }
    return server.getConsent();
  }

  public void syncServer() {
    try {
      ArrayList<Header> headers = new ArrayList<>();
      ServerRequest serverRequest = new ServerRequest();
      Server server = this.findOne();
      if (shouldSync(server)) {
        serverRequest.setServerUuid(server.getServerUuid());
        serverRequest.setServerVersion(applicationConfig.getServerVersion());
        serverRequest.setServerOs(server.getServerOs());
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
        HttpResponse<String> response = httpClient.put(testsigmaOSConfigService.getUrl()
            + URLConstants.TESTSIGMA_OS_PUBLIC_SERVERS_URL + "/" + server.getServerUuid(), headers, serverRequest,
          new TypeReference<>() {
          });

        log.info("Response from server sync - " + response);
      } else {
        log.info("Skipping server sync since consent was not given....");
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  public void update(Server server) {
    this.serverRepository.save(server);
  }
}
