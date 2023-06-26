package com.mita.service.testproject;


import com.mita.repository.UserOnboardingRepository;
import com.mita.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserOnboardingService {

    private final UserOnboardingRepository userOnboardingRepository;

    public Long getCurrentUserId(){

        String email = CurrentUserService.getCurrentUser().getEmail();
        log.info(" current email"+email);
        Map<String, Object> userData = userOnboardingRepository.findByUserDetails(email);
        log.info(" current userid"+userData.get("id"));
        Long userId = (new BigInteger(userData.get("id").toString())).longValue();

        return userId;
    }
}
