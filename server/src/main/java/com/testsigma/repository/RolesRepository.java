package com.testsigma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.testsigma.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

    @Query(value = "select * from roles where role_name=?1",nativeQuery = true)
    Roles getRolesByRoleName(String roleName);
}
