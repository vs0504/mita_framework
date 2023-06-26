package com.mita.controller;

import com.mita.config.ApplicationConfig;
import com.mita.exception.NotLocalAgentRegistrationException;
import com.mita.exception.TestsigmaException;
import com.mita.model.Agent;
import com.mita.service.AgentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/local/agents")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@CrossOrigin
public class LocalAgentsController {
  private final AgentService agentService;
  private final ApplicationConfig applicationConfig;

  @RequestMapping(path = "/register/{hostName}", method = RequestMethod.GET)
  public void registerAgent(@PathVariable("hostName") String hostName, HttpServletRequest request)
    throws TestsigmaException {
    if(request.getServerName().equals("localhost") && !applicationConfig.getIsDockerEnv()) {
      Agent agent = new Agent();
      agent.setTitle(hostName);
      agent.setIpAddress("127.0.0.1");
      log.info("Agent ------> " + agent);
      log.info("Server Host Name: " + request.getServerName());
      log.info("Server Host header: " + request.getHeader("Host"));
      agentService.createLocalAgent(agent);
    } else {
      throw new NotLocalAgentRegistrationException("Not a local agent registration");
    }
  }
}
