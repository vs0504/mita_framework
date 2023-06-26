package com.mita.service;

import com.mita.model.RolePermissionMapping;

import java.util.List;

public interface RolePermissionMappingService {
    List<RolePermissionMapping> findRolePermissionsByRoleId(Object roleId);
}
