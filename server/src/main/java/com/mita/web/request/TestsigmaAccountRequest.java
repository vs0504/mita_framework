package com.mita.web.request;

import com.mita.model.RegistrationMedium;
import com.mita.model.RegistrationType;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;

@Data
public class TestsigmaAccountRequest {
  private String firstName;
  private String lastName;
  private String email;
  private String serverUuid;
  private String serverVersion;
  private String serverOs;
  private Timestamp lastUpTime = Timestamp.from(Instant.now());
  private Boolean productUpdates;
  private Boolean communityAccess;
  private RegistrationType registrationType;
  private RegistrationMedium registrationMedium;
}
