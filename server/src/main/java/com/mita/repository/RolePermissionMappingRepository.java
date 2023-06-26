package com.mita.repository;


import com.mita.model.RolePermissionMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionMappingRepository extends JpaRepository<RolePermissionMapping,Integer> {
    @Query(value = "Select * from role_permission_mapping where role_id = ?1",nativeQuery = true)
    List<RolePermissionMapping> findRolePermissionMappingByRoleId(Long rolePermissionId);
}
