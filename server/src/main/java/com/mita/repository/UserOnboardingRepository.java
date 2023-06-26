package com.mita.repository;

import com.mita.model.UserOnboardingDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Repository
@Transactional
public interface UserOnboardingRepository extends BaseRepository<UserOnboardingDetails, Long>{

	@Query(value = "SELECT * FROM user_onboarding_details where email=?1", nativeQuery = true)
	Map<String, Object> findByUserDetails(String email);

	@Query(value = "SELECT * FROM user_onboarding_details where user_name=?1", nativeQuery = true)
	UserOnboardingDetails findByUserDetailsByUserName(String name);
}
