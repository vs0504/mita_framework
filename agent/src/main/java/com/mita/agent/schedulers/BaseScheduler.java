package com.mita.agent.schedulers;

import com.mita.agent.config.AgentConfig;
import com.mita.agent.exception.MitaException;
import com.mita.agent.http.WebAppHttpClient;
import com.mita.agent.mobile.MobileAutomationServerService;
import com.mita.agent.mobile.DeviceContainer;
import com.mita.agent.mobile.android.AndroidDeviceListener;
import com.mita.agent.mobile.ios.IosDeviceListener;
import com.mita.agent.mobile.ios.IosDeviceService;
import com.mita.agent.services.AgentBrowserService;
import com.mita.agent.ws.server.AgentWebServer;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public abstract class BaseScheduler {

  @Setter
  private static boolean skip = false;
  protected final WebApplicationContext webApplicationContext;
  protected final AgentConfig agentConfig;
  protected final WebAppHttpClient httpClient;
  protected final DeviceContainer deviceContainer;
  protected final MobileAutomationServerService mobileAutomationServerService;
  protected final IosDeviceService iosDeviceService;
  protected final AndroidDeviceListener androidDeviceListener;
  protected final IosDeviceListener iosDeviceListener;
  protected final AgentWebServer agentWebServer;
  protected final AgentBrowserService agentBrowserService;

  public BaseScheduler(WebApplicationContext webApplicationContext) {
    this.webApplicationContext = webApplicationContext;
    this.agentConfig = webApplicationContext.getBean(AgentConfig.class);
    this.httpClient = webApplicationContext.getBean(WebAppHttpClient.class);
    this.deviceContainer = webApplicationContext.getBean(DeviceContainer.class);
    this.mobileAutomationServerService = webApplicationContext.getBean(MobileAutomationServerService.class);
    this.iosDeviceService = webApplicationContext.getBean(IosDeviceService.class);
    this.androidDeviceListener = webApplicationContext.getBean(AndroidDeviceListener.class);
    this.iosDeviceListener = webApplicationContext.getBean(IosDeviceListener.class);
    this.agentWebServer = webApplicationContext.getBean(AgentWebServer.class);
    this.agentBrowserService = webApplicationContext.getBean(AgentBrowserService.class);
  }

  protected boolean skipScheduleRun() {

    log.debug("Checking if scheduler run needs to be skipped.....");

    if (agentConfig.getRegistered().equals(Boolean.FALSE)) {
      log.debug("Skipping scheduler run because agent is not yet registered...");
      skip = true;
    }

    return skip;
  }

  public void deRegisterAgent(Exception e) {
    log.error(e.getMessage(), e);
    try {
      androidDeviceListener.removeDeviceListenerCallback();
      iosDeviceListener.removeDeviceListenerCallback();
      deviceContainer.disconnectDevices();
      agentWebServer.stopWebServerConnectors();
      skip = true;
    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
    }
    agentConfig.setRegistered("false");
    agentConfig.setJwtApiKey(null);
    agentConfig.setUUID(null);
    skip = true;
    try {
      agentConfig.removeConfig();
    } catch (MitaException ex) {
      log.error(e.getMessage(), e);
    }
  }

}
