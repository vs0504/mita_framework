

package com.mita.repository;

import com.mita.model.UploadType;
import com.mita.model.UploadVersion;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UploadVersionRepository extends BaseRepository<UploadVersion, Long> {
  List<UploadVersion> findAllByLastUploadedTimeBeforeAndUploadTypeIn(Timestamp lastUploadedTime, Collection<UploadType> uploadType);

  List<UploadVersion> findAllByUploadTypeIn(Collection<UploadType> uploadType);
  List<UploadVersion> findAllByUploadId(Long uploadId);


  Optional<UploadVersion> findByNameAndUploadId(String name, Long importedId);

    Optional<UploadVersion> findAllByUploadIdAndImportedId(Long uploadId, Long id);
}
