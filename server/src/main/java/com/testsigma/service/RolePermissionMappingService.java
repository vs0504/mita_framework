package com.testsigma.service;

import com.testsigma.model.RolePermissionMapping;
import com.testsigma.model.Roles;

import java.util.List;

public interface RolePermissionMappingService {
    List<RolePermissionMapping> findRolePermissionsByRoleId(Object roleId);
}
