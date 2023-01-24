package com.testsigma.service;

import com.testsigma.model.UserOnboardingDetails;
import com.testsigma.repository.UserOnboardingRepository;
import com.testsigma.web.request.OnboardingRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OnboardService {

    @Autowired
    private UserOnboardingRepository userOnboardingRepository;

    public String saveUserDetails(OnboardingRequest onboardingRequest) {

        try {
            UserOnboardingDetails userData = new UserOnboardingDetails();

            BeanUtils.copyProperties(onboardingRequest, userData);

            log.info("userData.."+userData );
            UserOnboardingDetails savedData = userOnboardingRepository.save(userData);

            if (savedData != null) {
                log.info("savedData" +savedData);
                return "User Has Onboarded Successfully";
            }

        } catch (Exception e) {

            log.error(e.getMessage(), e);
        }
        return null;
    }
}
