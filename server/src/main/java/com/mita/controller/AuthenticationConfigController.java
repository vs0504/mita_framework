package com.mita.controller;

import com.mita.config.AdditionalPropertiesConfig;
import com.mita.dto.AuthenticationConfigDTO;
import com.mita.exception.MitaException;
import com.mita.mapper.AuthenticationConfigMapper;
import com.mita.model.AuthenticationType;
import com.mita.service.JWTTokenService;
import com.mita.web.request.AuthenticationConfigRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth_config")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class AuthenticationConfigController {
  private final AuthenticationConfigMapper mapper;
  private final AdditionalPropertiesConfig authConfig;

  @RequestMapping(method = RequestMethod.GET)
  public AuthenticationConfigDTO getConfig() {
    return mapper.map(authConfig);
  }

  @RequestMapping(method = RequestMethod.PUT, path = "")
  public AuthenticationConfigDTO update(@RequestBody AuthenticationConfigRequest request) throws MitaException {

    mapper.merge(request, authConfig);
    authConfig.saveConfig();
    return mapper.map(authConfig);
  }


  @PutMapping("/regenerate/{type}")
  public void regenerateKey(@PathVariable("type") String typeString) throws MitaException {
    AuthenticationType type = AuthenticationType.valueOf(typeString);
    String randomKey = String.valueOf(UUID.randomUUID()).replace("-", "");
    if (type == AuthenticationType.API) {
      authConfig.setApiKey(randomKey);
      authConfig.saveConfig();
    }
    if (type == AuthenticationType.JWT) {
      authConfig.setJwtSecret(randomKey);
      authConfig.saveConfig();
      JWTTokenService.setJWT_SECRET(randomKey);
    }
  }
}
