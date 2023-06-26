package com.mita.controller;

import com.mita.repository.UserOnboardingRepository;
import com.mita.model.Permissions;
import com.mita.model.RolePermissionMapping;
import com.mita.service.CurrentUserService;
import com.mita.service.PermissionService;
import com.mita.service.RolePermissionMappingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/role_permission_mapping")
@Log4j2
@CrossOrigin("*")
public class RolePermissionMappingController {

    @Autowired
    private UserOnboardingRepository userOnboardingRepository;

    @Autowired
    private RolePermissionMappingService rolePermissionMappingService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public Map getPermissions(){

        log.info("Request /workspace_versions/");
        String email = CurrentUserService.getCurrentUser().getEmail();
        log.info(" current email"+email);
        Map<String, Object> userData = userOnboardingRepository.findByUserDetails(email);
        List<RolePermissionMapping> rolePermissionMappingsList= rolePermissionMappingService.findRolePermissionsByRoleId(userData.get("role_id"));
        List<Permissions> permissions = permissionService.getAllPermissions();

        Map<String,Boolean> object = new HashMap<>();

        for(Permissions  permissionObject: permissions) {
            List<RolePermissionMapping> rolePermissionMappingResultList = rolePermissionMappingsList.stream().filter(project -> project.getPermissionId().toString().equalsIgnoreCase(permissionObject.getPermissionId().toString())).collect(Collectors.toList());
            if (rolePermissionMappingResultList.size() > 0 && rolePermissionMappingResultList.get(0).getPermissionStatus().equalsIgnoreCase("ACTIVE")) {
                object.put(permissionObject.getPermissionKey(),true);
            }else{
                object.put(permissionObject.getPermissionKey(),false);
            }
        }

        return object;
    }


}
