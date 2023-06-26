

package com.mita.repository;

import com.mita.model.SupportMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SupportMemebrsRepository extends JpaRepository<SupportMembers, Long> {
  List<SupportMembers> findAll();
}
