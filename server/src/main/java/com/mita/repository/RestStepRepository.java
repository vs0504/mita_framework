

package com.mita.repository;

import com.mita.model.RestStep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestStepRepository extends JpaRepository<RestStep, Long> {
  RestStep findByStepId(Long stepId);

    Optional<RestStep> findAllByStepIdAndImportedId(Long stepId, Long importedFrom);

  Page<RestStep> findAll(Specification<RestStep> specification, Pageable pageable);
}
