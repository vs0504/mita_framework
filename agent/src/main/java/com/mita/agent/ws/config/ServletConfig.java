package com.mita.agent.ws.config;

import com.mita.agent.ws.servlet.MobileFrameServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

  @Bean
  public ServletRegistrationBean<MobileFrameServlet> mobileFrameServlet() {
    return new ServletRegistrationBean<>(new MobileFrameServlet(), "/mobile-frames/*");
  }
}
