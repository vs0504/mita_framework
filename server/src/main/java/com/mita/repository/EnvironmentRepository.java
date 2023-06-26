

package com.mita.repository;

import com.mita.model.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface EnvironmentRepository extends BaseRepository<Environment, Long> {
    Optional<Environment> findByName(String name);
}
