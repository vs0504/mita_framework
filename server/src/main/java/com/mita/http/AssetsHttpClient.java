package com.mita.http;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AssetsHttpClient extends AutomatorHttpClient {
  @Autowired
  public AssetsHttpClient() {
    super();
  }
}
