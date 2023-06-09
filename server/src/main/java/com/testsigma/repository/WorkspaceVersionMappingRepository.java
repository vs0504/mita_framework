package com.testsigma.repository;

import com.testsigma.model.WorkspaceVersionMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WorkspaceVersionMappingRepository extends JpaRepository<WorkspaceVersionMapping, Long> {

    @Query(value = "SELECT * from workspaces_version_mapping where user_id=?1", nativeQuery = true)
    List<Map> getWorkspaceVersionMappingByUserId(Long userId);


    @Query(value = "SELECT * from workspaces_version_mapping where user_id=?1" +
            " ", nativeQuery = true)
    Page<WorkspaceVersionMapping> findWorkSpaceVersionByUserId(Long id, Pageable pageable);
    @Query(value = "SELECT * from workspaces_version_mapping where user_id=?1" +
            " ", nativeQuery = true)
    List<WorkspaceVersionMapping> findWorkSpaceVersionByUserId1(Long userId);

    @Query(value = "SELECT * from workspaces_version_mapping where user_id=?1 and status=ACTIVE" +
            " ", nativeQuery = true)
    List<WorkspaceVersionMapping> findActiveWorkSpaceVersionByUserId(Long userId);
}
