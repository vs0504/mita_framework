package com.testsigma.controller;

import com.testsigma.config.AdditionalPropertiesConfig;
import com.testsigma.dto.ServerDTO;
import com.testsigma.exception.TestsigmaException;
import com.testsigma.mapper.ServerMapper;
import com.testsigma.model.Server;
import com.testsigma.service.OnboardService;
import com.testsigma.service.ServerService;
import com.testsigma.service.TestsigmaOSConfigService;
import com.testsigma.web.request.OnboardingRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(value = "/onboarding")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OnboardingController {

  private final TestsigmaOSConfigService osService;
  private final ServerService serverService;
  private final ServerMapper serverMapper;

  private final OnboardService onboardService;
  private final org.springframework.core.env.Environment environment;
  @Autowired
  private AdditionalPropertiesConfig additionalProperties;

  @GetMapping
  public ServerDTO getOnboardingPreference() throws TestsigmaException {
    return serverMapper.map(serverService.findOne());
  }

  @PostMapping
  public ResponseEntity<?> post(@RequestBody OnboardingRequest onboardingRequest) throws TestsigmaException {

    updateUsernameAndPassword(onboardingRequest);
//    if (onboardingRequest.getIsSendUpdates())
//      osService.createAccount(onboardingRequest);
//    setOnboardingDone();
    String response = onboardService.saveUserDetails(onboardingRequest);
    if( response != null)
      return ResponseEntity.status(HttpStatus.OK).body(response);
    else
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please check onboarding details properly!") ;

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
    additionalProperties.setUserName(request.getUsername());
    additionalProperties.setPassword(request.getPassword());
    additionalProperties.saveConfig();
  }


}

