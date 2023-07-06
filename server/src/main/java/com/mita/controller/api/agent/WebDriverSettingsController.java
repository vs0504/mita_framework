

package com.mita.controller.api.agent;

import com.mita.dto.WebDriverSettingsDTO;
import com.mita.exception.MitaException;
import com.mita.service.WebDriverSettingsService;
import com.mita.web.request.WebDriverSettingsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController(value = "agentWebDriverSettingsController")
@RequestMapping(path = "/api/agents/webdriver-settings")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class WebDriverSettingsController {

  private final WebDriverSettingsService webDriverSettingsService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public WebDriverSettingsDTO create(@RequestBody WebDriverSettingsRequest webDriverSettingsRequest) throws MitaException,
    SQLException, IOException {
    log.info("Received /api/agents/webdriver-settings request with data - " + webDriverSettingsRequest);
    WebDriverSettingsDTO webDriverSettingsDTO = webDriverSettingsService.getWebDriverSettings(webDriverSettingsRequest);
    log.info("Responding back with web driver settings DTO - " + webDriverSettingsDTO);
    return webDriverSettingsDTO;
  }

  @GetMapping(value = "/capabilities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public WebDriverSettingsDTO capabilities(@PathVariable("id") long id) throws MitaException,
    SQLException, IOException {
    log.info(String.format("Received /api/agents/webdriver-settings/capabilities/%s request", id));
    WebDriverSettingsDTO webDriverSettingsDTO = webDriverSettingsService.getCapabilities(id);
    log.info("Responding back with web driver settings DTO - " + webDriverSettingsDTO);
    return webDriverSettingsDTO;
  }
}
