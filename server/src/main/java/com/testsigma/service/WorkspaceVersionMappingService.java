package com.testsigma.service;


import com.testsigma.model.WorkspaceVersionMapping;
import com.testsigma.repository.WorkspaceVersionMappingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WorkspaceVersionMappingService {
    private final WorkspaceVersionMappingRepository workspaceVersionMappingRepository;

    public Page<?> findWorkSpaceVersionByUserId(Object id, Pageable pageable) {
        Long userId = (new BigInteger(id.toString())).longValue();
        return workspaceVersionMappingRepository.findWorkSpaceVersionByUserId(userId,pageable);
    }

    public List<WorkspaceVersionMapping> findWorkSpaceVersionByUserId1(Object id) {
        Long userId = (new BigInteger(id.toString())).longValue();
        return workspaceVersionMappingRepository.findWorkSpaceVersionByUserId1(userId);

    }
}
