

package com.mita.repository;

import com.mita.model.BackupDetail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BackupDetailRepository extends PagingAndSortingRepository<BackupDetail, Long> {
}
