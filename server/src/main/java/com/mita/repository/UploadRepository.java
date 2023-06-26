

package com.mita.repository;

import com.mita.model.Upload;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UploadRepository extends BaseRepository<Upload, Long> {

    Optional<Upload> findAllByWorkspaceIdAndImportedId(Long applicationId, Long id);

    Optional<Upload> findByNameAndWorkspaceId(String name, Long applicationVersionId);

    List<Upload> findAllByWorkspaceId(Long applicationVersionId);

    Optional<Upload> findByImportedIdAndWorkspaceId(Long importedId, Long applicationId);
}
