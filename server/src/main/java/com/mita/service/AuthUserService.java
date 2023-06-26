package com.mita.service;

import com.mita.repository.UserOnboardingRepository;
import com.mita.config.AdditionalPropertiesConfig;
import com.mita.model.AuthUser;
import com.mita.model.AuthenticationType;
import com.mita.model.UserOnboardingDetails;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthUserService implements UserDetailsService, OAuth2UserService<OidcUserRequest, OidcUser> {

  private final AdditionalPropertiesConfig authenticationConfig;
  @Setter
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  private final ServerService serverService;

  @Autowired
  private UserOnboardingRepository userOnboardingRepository;
  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    AuthUser authUser = new AuthUser();
    authUser.setUuid(UUID.randomUUID().toString());
    UserOnboardingDetails userDetails = userOnboardingRepository.findByUserDetailsByUserName(name);
    setServerUuid(authUser);
    switch (authenticationConfig.getAuthenticationType()) {
      case FORM:
        authUser.setEmail(userDetails.getEmail());
        authUser.setUserName(userDetails.getUsername());
        authUser.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        authUser.setAuthenticationType(AuthenticationType.FORM);
        if (!authUser.getUsername().equals(name)) {
          throw new UsernameNotFoundException("Unable to find user with name - " + name);
        }
        break;
      case API:
        authUser.setAuthenticationType(AuthenticationType.API);
        break;
      case NO_AUTH:
        authUser.setAuthenticationType(AuthenticationType.NO_AUTH);
        break;
      case JWT:
        authUser.setAuthenticationType(AuthenticationType.JWT);
        break;
      case OIDC:
        authUser.setAuthenticationType(AuthenticationType.OIDC);
        break;
      default:
        throw new UsernameNotFoundException("Unable to find user with name - " + name);
    }
    return authUser;
  }

  @Override
  public OidcUser loadUser(OidcUserRequest oidcUserRequest) throws OAuth2AuthenticationException {
    OidcUser oidcUser = new OidcUserService().loadUser(oidcUserRequest);
    try {
      AuthUser authUser = new AuthUser();
      setServerUuid(authUser);
      String email = oidcUser.getAttributes().get("email").toString();
      if (StringUtils.isEmpty(email))
        throw new UsernameNotFoundException("Unable to find user - " + email);
      authUser.setEmail(oidcUser.getEmail());
      authUser.setUserName(oidcUser.getFullName());
      authUser.setClaims(oidcUser.getClaims());
      authUser.setUserInfo(oidcUser.getUserInfo());
      authUser.setIdToken(oidcUser.getIdToken());
      authUser.setAuthenticationType(AuthenticationType.OIDC);
      authUser.setUuid(UUID.randomUUID().toString());
      return authUser;
    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
      throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
    }
  }

  private void setServerUuid(AuthUser authUser) {
    try {
      if (StringUtils.isEmpty(authUser.getServerUuid())) {
        authUser.setServerUuid(serverService.findOne().getServerUuid());
      }
    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
    }
  }
}
