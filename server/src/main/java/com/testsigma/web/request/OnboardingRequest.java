package com.testsigma.web.request;

import com.testsigma.model.RegistrationMedium;
import com.testsigma.model.RegistrationType;
import lombok.Data;

@Data
public class OnboardingRequest {

  private String firstName;
  private String lastName;
  private String username;
  private String password;
  private String email;
  private Boolean isCommunityAccess;
  private Boolean isSendUpdates;
  private RegistrationType registrationType;
  private RegistrationMedium registrationMedium;
  private String apiKey;
  private String jwtSecret;
  private Boolean isApiEnabled;
  private String authenticationType;
  private String googleClientId;
  private String googleClientSecret;

}
