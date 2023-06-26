

package com.mita.repository;

import com.mita.model.AbstractTestSuite;
import com.mita.model.SuiteTestCaseMapping;
import com.mita.model.TestCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface SuiteTestCaseMappingRepository extends JpaRepository<SuiteTestCaseMapping, Long> {

  Optional<SuiteTestCaseMapping> findFirstByTestSuiteAndTestCase(AbstractTestSuite testSuite, TestCase testCase);

  Optional<SuiteTestCaseMapping> findById(Long id);

  List<SuiteTestCaseMapping> findAllBySuiteId(Long suiteId);

  @Query("SELECT suiteTestCaseMapping FROM SuiteTestCaseMapping suiteTestCaseMapping WHERE suiteTestCaseMapping.testCaseId IN(:testCaseIds) and suiteTestCaseMapping.suiteId = :suiteId")
  List<SuiteTestCaseMapping> findBySuiteIdAndTestCaseIds(@Param("suiteId") Long suiteId, @Param("testCaseIds") List<Long> testCaseIds);

  @Query("SELECT suiteTestCaseMapping.testCaseId FROM SuiteTestCaseMapping suiteTestCaseMapping WHERE suiteTestCaseMapping.suiteId = :id ORDER BY suiteTestCaseMapping.position")
  List<Long> findTestCaseIdsByTestSuiteId(Long id);

    Page<SuiteTestCaseMapping> findAll(Specification specification, Pageable pageRequest);

    Optional<SuiteTestCaseMapping> findAllBySuiteIdInAndImportedId(List<Long> ids, Long id);
}
