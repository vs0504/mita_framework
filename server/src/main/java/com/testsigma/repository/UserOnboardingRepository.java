package com.testsigma.repository;

import com.testsigma.model.Addon;
import com.testsigma.model.UserOnboardingDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserOnboardingRepository extends BaseRepository<UserOnboardingDetails, Long>{
}
