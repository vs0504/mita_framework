

package com.mita.service;

import com.mita.model.AuthUser;
import com.mita.model.UserPreference;
import com.mita.model.WorkspaceVersionMapping;
import com.mita.repository.UserOnboardingRepository;
import com.mita.repository.UserPreferenceRepository;
import com.mita.repository.WorkspaceVersionMappingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserPreferenceService {
  private final UserPreferenceRepository userPreferenceRepository;
  private final WorkspaceService workspaceService;
  private final WorkspaceVersionService workspaceVersionService;
  private final WorkspaceVersionMappingRepository workspaceVersionMappingRepository;
  private final UserOnboardingRepository userOnboardingRepository;

  public UserPreference findByEmail(String email) {
    return this.userPreferenceRepository.findByEmail(email).orElse(null);
  }

  public UserPreference save(UserPreference userPreference) {
    return this.userPreferenceRepository.save(userPreference);
  }

  public void insertDefaultUserPreferences(AuthUser authUser) {
    UserPreference userPreference = this.findByEmail(authUser.getEmail());
    Map<String, Object> userData = userOnboardingRepository.findByUserDetails(authUser.getEmail());
    Long userId = (new BigInteger(userData.get("id").toString())).longValue();
    if (userPreference == null) {
      userPreference = new UserPreference();
      //Workspace workspace = workspaceService.findFirstWebDemoApplication();
      //WorkspaceVersion applicationVersion = workspaceVersionService.findFirstByWorkspaceId(workspace.getId());

     List<WorkspaceVersionMapping> workspaceVersionMappingList = workspaceVersionMappingRepository.findActiveWorkSpaceVersionByUserId(userId);
     System.out.println("workspaceVersionMappingList size"+workspaceVersionMappingList.size());
     if(workspaceVersionMappingList.size()>0)
     userPreference.setVersionId(workspaceVersionMappingList.get(0).getWorkspaceVersionId());
      userPreference.setEmail(authUser.getEmail());
      userPreferenceRepository.save(userPreference);
    }
  }
}
