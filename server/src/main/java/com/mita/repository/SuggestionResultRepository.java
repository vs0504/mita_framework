

package com.mita.repository;

import com.mita.model.SuggestionResultMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SuggestionResultRepository extends JpaRepository<SuggestionResultMapping, Long> {
  Page<SuggestionResultMapping> findAllByStepResultId(Long stepResultId, Pageable pageable);
}
