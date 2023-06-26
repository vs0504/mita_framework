package com.mita.service;

import com.mita.repository.PermissionsRepository;
import com.mita.model.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionsRepository permissionsRepository;
    @Override
    public List<Permissions> getAllPermissions() {
        return permissionsRepository.findAll();
    }
}
