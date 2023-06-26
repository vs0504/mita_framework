package com.mita;

import com.mita.repository.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = "com.mita.repository", repositoryBaseClass = BaseRepositoryImpl.class)
@EnableScheduling
@EnableAsync
@EnableWebMvc
@EnableJpaAuditing
public class MitaWebApplication {
  public static void main(String[] args) {
    try {
      System.setProperty("com.sun.security.enableAIAcaIssuers", "true");
      SpringApplication application = new SpringApplication(MitaWebApplication.class);
      application.addListeners(new ApplicationPidFileWriter("./bin/app.pid"));
      application.run();
    } catch (Throwable t) {
      t.printStackTrace();
    }
  }
}
