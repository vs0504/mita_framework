package com.testsigma.model;


import lombok.Data;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Data
@ToString
@Table(name = "permissions")
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    @Column(name = "permission_name")
    private String permissionName;

    @Column(name = "permission_description")
    private String permissionDescription;

    @Column(name = "permission_key")
    private String permissionKey;

//    @ManyToMany(mappedBy = "permissions",fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<Roles> roles;

//    @ManyToOne
//    @JoinColumn(name = "role_id")
//    private Roles roles;
}

