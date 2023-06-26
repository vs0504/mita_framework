

package com.mita.repository;

import com.mita.model.ResultConstant;
import com.mita.model.TestCaseDataDrivenResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TestCaseDataDrivenResultRepository extends JpaRepository<TestCaseDataDrivenResult, Long> {

  void deleteByIterationResultId(Long iterationResultId);

  @Query("select tcddr from TestCaseDataDrivenResult tcddr inner join TestCaseResult tcr on tcddr.iterationResultId = tcr.id " +
          "Where tcddr.testCaseResultId=:testCaseResultId and tcr.result <>:result")
  List<TestCaseDataDrivenResult> findByTestCaseResultIdAndResultNot(Long testCaseResultId, ResultConstant result);

  List<TestCaseDataDrivenResult> findAllByTestCaseResultId(Long testCaseResultId);

  Page<TestCaseDataDrivenResult> findAll(Specification<TestCaseDataDrivenResult> spec, Pageable pageable);
}
