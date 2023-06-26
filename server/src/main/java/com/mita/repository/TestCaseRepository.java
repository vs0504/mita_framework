

package com.mita.repository;

import com.mita.dto.TestCaseStatusBreakUpDTO;
import com.mita.dto.TestCaseTypeBreakUpDTO;
import com.mita.model.TestCase;
import com.mita.model.TestCaseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TestCaseRepository extends PagingAndSortingRepository<TestCase, Long>, JpaSpecificationExecutor<TestCase>, JpaRepository<TestCase, Long> {

  @Query("SELECT testCase FROM TestCase AS testCase " +
    "JOIN FETCH SuiteTestCaseMapping AS suiteMapping " +
    "ON suiteMapping.suiteId = :suiteId AND suiteMapping.testCaseId = testCase.id " +
    "ORDER BY suiteMapping.position ASC")
  List<TestCase> findAllBySuiteId(@Param("suiteId") Long suiteId);

  List<TestCase> findAllByWorkspaceVersionId(Long workspaceVersionId);

  Optional<TestCase> findByNameAndWorkspaceVersionId(String name, Long workspaceVersionId);

  Page<TestCase> findAllByWorkspaceVersionIdAndIsStepGroupAndStatus(Long workspaceVersionId, Boolean isStepGroup, TestCaseStatus status, Pageable pageable);

  @Query("SELECT testCase FROM TestCase AS testCase " +
    "JOIN FETCH testCase.results AS caseResult " +
    "JOIN FETCH caseResult.testDeviceResult as environmenResult " +
    "JOIN FETCH caseResult.testSuite as suite " +
    "JOIN FETCH environmenResult.testDevice as testDevice " +
    "WHERE environmenResult.testPlanResultId = :testPlanResultId " +
    "AND caseResult.result IN (" +
    "com.mita.model.ResultConstant.ABORTED, com.mita.model.ResultConstant.FAILURE, " +
    "com.mita.model.ResultConstant.NOT_EXECUTED) AND caseResult.isStepGroup = FALSE AND " +
    "caseResult.iteration IS NULL"
  )
  List<TestCase> findAllFailureBytestPlanResultId(@Param("testPlanResultId") Long testPlanResultId);

  @Modifying
  @Query("UPDATE TestCase tc SET tc.deleted = true, tc.isActive = NULL WHERE tc.id in :ids")
  Integer markAsDelete(@Param("ids") List<Long> ids);

  void deleteAllByWorkspaceVersionId(@Param("workspaceVersionId") Long workspaceVersionId);

  @Modifying
  @Query("UPDATE TestCase tc SET tc.deleted = false, tc.isActive = true WHERE tc.id =:id")
  Integer markAsRestored(@Param("id") Long id);

  Page<TestCase> findAllByTestDataId(Long testDataId, Pageable pageable);

  Page<TestCase> findAllByPreRequisite(Long preRequisite, Pageable pageable);

  @Query("SELECT COUNT(id) FROM TestCase WHERE workspaceVersionId=:versionId AND deleted <> true AND isStepGroup = false")
  Long countByVersion(@Param("versionId") Long versionId);

  @Query("SELECT COUNT(id) FROM TestCase WHERE preRequisite=:testCaseId AND deleted <> true")
  Long countByPreRequisite(@Param("testCaseId") Long testCaseId);

  @Query("SELECT id FROM TestCase WHERE preRequisite=:testCaseId AND deleted <> true")
  List<Long> getTestCaseIdsByPreRequisite(@Param("testCaseId") Long testCaseId);

  @Query("SELECT new com.mita.dto.TestCaseStatusBreakUpDTO(MAX(status), COUNT(id)) FROM TestCase WHERE deleted = false AND isStepGroup = false AND workspaceVersionId =:versionId GROUP BY status")
  List<TestCaseStatusBreakUpDTO> breakUpByStatus(@Param("versionId") Long versionId);

  @Query("SELECT new com.mita.dto.TestCaseTypeBreakUpDTO(type, COUNT(id)) FROM TestCase WHERE deleted = false AND isStepGroup = false AND workspaceVersionId =:versionId GROUP BY type")
  List<TestCaseTypeBreakUpDTO> breakUpByType(@Param("versionId") Long versionId);

  @Query(value = "SELECT testCase.id FROM TestCase as testCase " +
    "JOIN testCase.testData as testData WHERE testData.id = :testDataId")
  List<Long> findTestCaseIdsByTestDataId(@Param("testDataId") Long testDataId);

  List<TestCase> findAllByName(String name);

  Optional<TestCase> findTestCaseByWorkspaceVersionIdAndName(Long versionId, String name);

  Optional<TestCase> findAllByWorkspaceVersionIdAndImportedId(Long versionId, Long importedId);
}