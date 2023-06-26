package com.mita.repository;

import com.mita.model.ExternalImportNlpMapping;
import com.mita.model.ExternalImportType;
import com.mita.model.WorkspaceType;

import java.util.Optional;

public interface ExternalImportNlpMappingRepository extends BaseRepository<ExternalImportNlpMapping, Long> {

    Optional<ExternalImportNlpMapping> findByExternalNlpIdAndAndExternalImportTypeAndWorkspaceType(String externalNlpId,
                                                                                                   ExternalImportType type,
                                                                                                   WorkspaceType applicationType);


}
