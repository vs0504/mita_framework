
package com.mita.repository;

import com.mita.model.ProvisioningProfileUpload;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProvisioningProfileUploadRepository extends BaseRepository<ProvisioningProfileUpload, Long> {

  List<ProvisioningProfileUpload> findAllByProvisioningProfileId(Long profileId);

  ProvisioningProfileUpload findByProvisioningProfileIdAndUploadVersionId(Long provisioningProfileId, Long uploadId);

  List<ProvisioningProfileUpload> findAllByUploadVersionId(Long uploadId);
}
