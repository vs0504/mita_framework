

package com.mita.agent;

import com.mita.agent.init.WrapperConnector;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableScheduling
@EnableWebMvc
@SpringBootApplication(scanBasePackages = {"com.mita.agent", "com.mita.automator"})
@Log4j2
public class MitaAgent {
  public static void main(String[] args) {
    String wrapperPort = System.getProperty("agent.wrapper.port");
    if (StringUtils.isNotBlank(wrapperPort)) {
      WrapperConnector.getInstance().disconnectHook();

    }
    Thread.currentThread().setName("MitaAgent");
    ConfigurableApplicationContext c = SpringApplication.run(MitaAgent.class, args);
    if (StringUtils.isNotBlank(wrapperPort)) {
      WrapperConnector.getInstance().connect();
    }
  }
}
