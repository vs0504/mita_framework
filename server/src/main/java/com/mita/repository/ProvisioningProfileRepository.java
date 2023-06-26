
package com.mita.repository;

import com.mita.model.ProvisioningProfile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProvisioningProfileRepository extends BaseRepository<ProvisioningProfile, Long> {

}
