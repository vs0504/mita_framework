

package com.mita.repository;

import com.mita.model.DryTestSuite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DryTestSuiteRepository extends PagingAndSortingRepository<DryTestSuite, Long>,
  JpaSpecificationExecutor<DryTestSuite>, JpaRepository<DryTestSuite, Long> {
}
