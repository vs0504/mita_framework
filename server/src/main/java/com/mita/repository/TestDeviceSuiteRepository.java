

package com.mita.repository;

import com.mita.model.AbstractTestSuite;
import com.mita.model.TestDevice;
import com.mita.model.TestDeviceSuite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TestDeviceSuiteRepository extends JpaRepository<TestDeviceSuite, Long> {

  Optional<TestDeviceSuite> findFirstByTestDeviceAndTestSuite(TestDevice testDevice,
                                                              AbstractTestSuite testSuite);

  List<TestDeviceSuite> findAllByTestDeviceIdOrderByPosition(Long environmentId);

  @Query("SELECT suiteMapping FROM TestDeviceSuite suiteMapping WHERE suiteMapping.suiteId IN (:suiteIds) and suiteMapping.testDeviceId = :testDeviceId ")
  List<TestDeviceSuite> findByTestDeviceIdAndSuiteIds(@Param("testDeviceId") Long testDeviceId, @Param("suiteIds") List<Long> suiteIds);

  @Query("SELECT testDevice.suiteId FROM TestDeviceSuite testDevice WHERE testDevice.testDeviceId = :id ")
  List<Long> findSuiteIdsByTestDeviceId(Long id);
}
