

package com.mita.repository;

import com.mita.model.NaturalTextActionExample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface NaturalTextActionExamplesRepository extends JpaRepository<NaturalTextActionExample, Long> {
  Optional<NaturalTextActionExample> findByNaturalTextActionId(Long NaturalTextActionId);
}
