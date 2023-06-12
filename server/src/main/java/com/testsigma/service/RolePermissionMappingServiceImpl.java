package com.testsigma.service;

import com.testsigma.model.RolePermissionMapping;
import com.testsigma.model.Roles;
import com.testsigma.repository.RolePermissionMappingRepository;
import com.testsigma.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class RolePermissionMappingServiceImpl implements RolePermissionMappingService {

    @Autowired
    private RolePermissionMappingRepository rolePermissionMappingRepository;

    @Override
    public List<RolePermissionMapping> findRolePermissionsByRoleId(Object roleId) {
        Long rolePermissionId = (new BigInteger(roleId.toString())).longValue();
        return rolePermissionMappingRepository.findRolePermissionMappingByRoleId(rolePermissionId);
    }
}
