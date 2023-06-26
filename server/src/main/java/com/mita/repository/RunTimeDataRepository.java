

package com.mita.repository;

import com.mita.model.RunTimeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional
public interface RunTimeDataRepository extends JpaRepository<RunTimeData, Long> {
  Optional<RunTimeData> findBySessionId(String idExecutionRunId);

  Optional<RunTimeData> findByTestPlanRunIdAndSessionIdIsNull(Long idExecutionRunId);

  Optional<RunTimeData> findByTestPlanRunIdAndSessionId(Long idExecutionRunId, String sessionId);
}
