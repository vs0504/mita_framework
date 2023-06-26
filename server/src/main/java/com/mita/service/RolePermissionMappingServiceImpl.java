package com.mita.service;

import com.mita.repository.RolePermissionMappingRepository;
import com.mita.model.RolePermissionMapping;
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
