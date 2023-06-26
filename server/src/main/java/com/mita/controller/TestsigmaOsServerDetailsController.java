package com.mita.controller;

import com.mita.config.ApplicationConfig;
import com.mita.dto.ServerDetailsDTO;
import com.mita.exception.TestsigmaException;
import com.mita.os.stats.service.TestsigmaOsServerDetailsService;
import com.mita.service.TestsigmaOSConfigService;
import com.mita.util.NetworkUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/os_server_details")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class TestsigmaOsServerDetailsController {
  private final TestsigmaOSConfigService osConfigService;
  private final TestsigmaOsServerDetailsService serverDetailsService;
  private final ApplicationConfig applicationConfig;

  @GetMapping
  public ServerDetailsDTO get() throws TestsigmaException {
    ServerDetailsDTO serverDetails = new ServerDetailsDTO();
    serverDetails.setServerVersion(applicationConfig.getServerVersion());
    serverDetails.setServerIp(NetworkUtil.getCurrentIpAddress());
    serverDetails.setTestsigmaLabIP(serverDetailsService.getTestsigmaLabIPs());
    return serverDetails;
  }

}
