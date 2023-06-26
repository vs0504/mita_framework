package com.mita.controller;

import com.mita.config.AdditionalPropertiesConfig;
import com.mita.dto.ServerDTO;
import com.mita.exception.TestsigmaException;
import com.mita.mapper.ServerMapper;
import com.mita.model.Server;
import com.mita.service.OnboardService;
import com.mita.service.ServerService;
import com.mita.service.TestsigmaOSConfigService;
import com.mita.web.request.OnboardingRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Log4j2
@RestController
@RequestMapping(value = "/onboarding")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class OnboardingController {

  private final TestsigmaOSConfigService osService;
  private final ServerService serverService;
  private final ServerMapper serverMapper;

  private final OnboardService onboardService;
  private final org.springframework.core.env.Environment environment;
  @Autowired
  private AdditionalPropertiesConfig additionalProperties;

  @Autowired
  private Environment env;

  @GetMapping
  public ServerDTO getOnboardingPreference() throws TestsigmaException {
    return serverMapper.map(serverService.findOne());
  }

  @PostMapping
  public String post(@RequestBody OnboardingRequest onboardingRequest) throws TestsigmaException {
    onboardingRequest.setApiKey(UUID.randomUUID().toString().replace("-", ""));
    onboardingRequest.setJwtSecret(UUID.randomUUID().toString().replace("-", ""));
    onboardingRequest.setGoogleClientId(env.getProperty("authentication.google.clientId"));
    onboardingRequest.setGoogleClientSecret(env.getProperty("authentication.google.clientSecret"));
    onboardingRequest.setIsCommunityAccess(Boolean.valueOf(env.getProperty("authentication.api.enabled")));
    onboardingRequest.setAuthenticationType(env.getProperty("authentication.type"));
    onboardingRequest.setIsApiEnabled(true);
    // updateUsernameAndPassword(onboardingRequest);
//    if (onboardingRequest.getIsSendUpdates())
//      osService.createAccount(onboardingRequest);
//    setOnboardingDone();

    String response = onboardService.saveUserDetails(onboardingRequest);

    return response;
//    if( response != null)
//      return ResponseEntity.status(HttpStatus.OK).body(response);
//    else
//      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please check onboarding details properly!");
  }
  
  @PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
		ResponseEntity<?> response = onboardService.loginUser(email, password);
      return response;
	}
  
  @RequestMapping(value = "/otp", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void getOTP(@RequestBody OnboardingRequest request) throws TestsigmaException {
    updateUsernameAndPassword(request);
    osService.getOTP(request);
  }


  @RequestMapping(value = "/activate/{otp}", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void activate(@PathVariable("otp") String otp) throws TestsigmaException {
    osService.activate(otp);
    setOnboardingDone();
  }

  public void setOnboardingDone() throws TestsigmaException {
    Server server = serverService.findOne();
    server.setOnboarded(true);
    server.setConsentRequestDone(true);
    serverService.update(server);
  }

  public void updateUsernameAndPassword(OnboardingRequest request) throws TestsigmaException {
   // additionalProperties.setUserName(request.getUsername());
   // additionalProperties.setPassword(request.getPassword());
    additionalProperties.saveConfig();
  }


}

