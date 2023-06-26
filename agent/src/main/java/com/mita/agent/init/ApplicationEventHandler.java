

package com.mita.agent.init;

import com.mita.agent.config.AgentConfig;
import com.mita.agent.config.ApplicationConfig;
import com.mita.agent.services.AgentBrowserService;
import com.mita.agent.services.AgentWebServerService;
import com.mita.automator.AutomatorConfig;
import com.mita.agent.tasks.CloudAppBridge;
import com.mita.agent.utils.PathUtil;
import com.mita.agent.ws.server.AgentWebServer;
import com.mita.automator.exceptions.AgentDeletedException;
import com.mita.automator.utilities.UploadThreadPool;
import lombok.extern.log4j.Log4j2;
import org.eclipse.jetty.server.Server;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.embedded.jetty.JettyWebServer;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationContext;

@Log4j2
public class ApplicationEventHandler {

  public void handleStartEvent() {
    log.info("-------------- Post App Context Initialized Actions Started --------------");
    try {
      System.setProperty("com.sun.security.enableAIAcaIssuers", "true");
      PathUtil.getInstance().setPathsFromContext();
      UploadThreadPool.getInstance().createPool();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    log.info("-------------- Post App Context Initialized Actions Finished --------------");
  }

  public void handleShutdownEvent() {
    log.info("-------------- Post App Context Destroyed Actions Started --------------");
    //UploadThreadPool.getInstance().closePool();
    log.info("-------------- Post App Context Destroyed Actions Finished --------------");
  }

  public void postAppContextReadyActions(ApplicationContext context) {
    log.info("-------------- Post App Context Ready Actions Started --------------");
    AgentConfig agentConfig = context.getBean(AgentConfig.class);
    CloudAppBridge cloudAppBridge = context.getBean(CloudAppBridge.class);
    ApplicationConfig applicationConfig = context.getBean(ApplicationConfig.class);
    AgentWebServerService agentWebServerService = context.getBean(AgentWebServerService.class);

    AutomatorConfig automatorConfig = AutomatorConfig.getInstance();
    automatorConfig.setCloudServerUrl(agentConfig.getLocalServerUrl());
    automatorConfig.setTestCaseFetchWaitInterval(applicationConfig.getTestCaseFetchWaitInterval());
    automatorConfig.setTestCaseDefaultMaxTries(applicationConfig.getTestCaseDefaultMaxTries());
    automatorConfig.setAppBridge(cloudAppBridge);
    automatorConfig.init();

    // AdbBridge adbBridge = context.getBean(AdbBridge.class);
    // MobileAutomationServer mobileAutomationServer = context.getBean(MobileAutomationServer.class);
    AgentBrowserService agentBrowserService = context.getBean(AgentBrowserService.class);
    // AndroidDeviceListener androidDeviceListener = context.getBean(AndroidDeviceListener.class);
    // IosDeviceListener iosDeviceListener = context.getBean(IosDeviceListener.class);
    AgentWebServer agentWebServer = context.getBean(AgentWebServer.class);
    agentWebServer.startWebServerConnectors();
    try {
      agentBrowserService.sync();
    } catch (AgentDeletedException e) {
      log.info("-------------- Post App Context Failed Agent is deleted --------------");
    }
    // androidDeviceListener.syncInitialDeviceStatus();
    // adbBridge.createBridge();
    // ExecutorService executorService = Executors.newSingleThreadExecutor();
    // executorService.submit(androidDeviceListener);
    // ExecutorService executorService1 = Executors.newSingleThreadExecutor();
    // executorService1.submit(iosDeviceListener);
    // mobileAutomationServer.start();
    agentWebServerService.registerLocalAgent();
    log.info("-------------- Post App Context Ready Actions Finished --------------");
  }

  public void runPostWebContextReadyActions(WebServerInitializedEvent event) {
    log.info("-------------- Post Web Context Ready Actions Started --------------");
    WebServerApplicationContext context = event.getApplicationContext();
    AgentWebServer agentWebServer = context.getBean(AgentWebServer.class);
    WebServer webServer = event.getWebServer();
    Server server = ((JettyWebServer) webServer).getServer();
    agentWebServer.setServer(server);
    log.info("-------------- Post Web Context Ready Actions Finished --------------");
  }
}
