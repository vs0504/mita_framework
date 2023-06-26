
package com.mita.repository;

import com.mita.model.Addon;
import com.mita.model.AddonStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface AddonRepository extends BaseRepository<Addon, Long> {
  Optional<Addon> findByExternalUniqueIdAndStatus(String externalUniqueId, AddonStatus status);
  Optional<Addon> findTopByExternalUniqueIdAndStatus(String externalUniqueId, AddonStatus status);

}
