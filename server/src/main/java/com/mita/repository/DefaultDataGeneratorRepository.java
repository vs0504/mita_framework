

package com.mita.repository;

import com.mita.model.DefaultDataGenerator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DefaultDataGeneratorRepository extends BaseRepository<DefaultDataGenerator, Long> {
}
