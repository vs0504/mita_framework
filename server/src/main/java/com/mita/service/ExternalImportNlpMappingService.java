package com.mita.service;

import com.mita.repository.ExternalImportNlpMappingRepository;
import com.mita.model.ExternalImportNlpMapping;
import com.mita.model.ExternalImportType;
import com.mita.model.WorkspaceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class ExternalImportNlpMappingService {

    private final ExternalImportNlpMappingRepository repository;


    public Optional<ExternalImportNlpMapping> findByExternalIdAndExternalImportTypeAndWorkspaceType(String externalId,
                                                                                                      ExternalImportType externalImportType,
                                                                                                      WorkspaceType workspaceType){
        return repository.findByExternalNlpIdAndAndExternalImportTypeAndWorkspaceType(externalId, externalImportType, workspaceType);
    }

}
