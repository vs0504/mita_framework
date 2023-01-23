package com.testsigma.repository;

import com.testsigma.model.TestDataSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TestDataSetRepository extends JpaRepository<TestDataSet, Long>, JpaSpecificationExecutor<TestDataSet> {

    List<TestDataSet> findAllByTestDataIdOrderByPosition(Long testDataId);

    @Query("SELECT tds from TestDataSet tds where tds.name in :setNames AND tds.testDataProfileId=:testDataProfileId")
    List<TestDataSet> findAllByNamesAndTestDataId(List<String> setNames, Long testDataProfileId);

    Optional<TestDataSet> findTestDataSetByTestDataIdAndAndName(Long profileId, String name);

    List<TestDataSet> findTestDataSetByTestDataId(Long profileId);
}