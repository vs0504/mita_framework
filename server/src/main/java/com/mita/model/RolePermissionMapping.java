package com.mita.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "role_permission_mapping")
public class RolePermissionMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rolePermissionId;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "permission_id")
    private Long permissionId;

    @Column(name = "permission_status")
    private String permissionStatus;

}
