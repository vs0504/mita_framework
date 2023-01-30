package com.testsigma.service;

import com.testsigma.model.UserOnboardingDetails;
import com.testsigma.repository.UserOnboardingRepository;
import com.testsigma.util.ResponseObject;
import com.testsigma.web.request.OnboardingRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class OnboardService {

    @Autowired
    private UserOnboardingRepository userOnboardingRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String saveUserDetails(OnboardingRequest onboardingRequest) {

        try {
            UserOnboardingDetails userData = new UserOnboardingDetails();
			//String password = bCryptPasswordEncoder.encode(onboardingRequest.getPassword());
			//onboardingRequest.setPassword(password);
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
    
    
    public ResponseEntity<?> loginUser(String username, String password) {
		Map<String, Object> optionalUser = userOnboardingRepository.findByUserDetails(username);
		if (!optionalUser.isEmpty()) {
			UserOnboardingDetails userDetails = new UserOnboardingDetails();
			Map<String, Object> map = new HashMap();
			Map<String, Object> details = new HashMap();
		//	PassWordEncryption p = new PassWordEncryption();
			boolean pwd = getpasswordDecode(password, (String) optionalUser.get("password"));

			if (optionalUser.size() > 0 && pwd) {
				userDetails.setPassword("");
				map.put("user_details", details);
				return new ResponseEntity<>(
						new ResponseObject(200, "Success", "Details"),
						HttpStatus.OK);
			} else if (null != optionalUser.get("password") && !(optionalUser.get("password").equals(password))) {
				return new ResponseEntity<>(new ResponseObject(404, "Fail",
						"Please enter correct password"), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ResponseObject(404, "Fail",
						"Email ID not registered, please register"), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(new ResponseObject(404, "Fail",
				"Email ID not registered, please register"), HttpStatus.OK);
	}

public boolean getpasswordDecode(String password, String encodedPassword) {
		
if(password.equals(encodedPassword))	{
	return true;
}
		return false;
	}
}
