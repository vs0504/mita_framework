package com.testsigma.repository;

import com.testsigma.model.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions,Integer> {

}
