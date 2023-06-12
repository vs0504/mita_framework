package com.testsigma.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(name = "role_name",nullable = false,unique = true)
    private String roleName;

    @Column(name = "role_description")
    private String roleDescription;

   // private List<Permissions> permissions;


//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "role_permissions",
//            joinColumns = {
//                @JoinColumn(name = "role_id",referencedColumnName = "roleId")
//            },
//            inverseJoinColumns = {
//                @JoinColumn(name = "permission_id",referencedColumnName = "permissionId")
//            }
//    )
//    private List<Permissions> permissions;

//    @OneToMany(mappedBy = "roles",cascade = CascadeType.ALL)
//    @JoinTable(name = "role_permissions",
//            joinColumns = {
//                @JoinColumn(name = "role_id",referencedColumnName = "roleId")
//            },
//            inverseJoinColumns = {
//                @JoinColumn(name = "permission_id",referencedColumnName = "permissionId")
//            }
//    )
//    private List<Integer> permissionId;


//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "role_id")
//    private List<RolePermission> rolePermissions;
}
