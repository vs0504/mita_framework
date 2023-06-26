

package com.mita.repository;

import com.mita.model.Integrations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface IntegrationsRepository extends JpaRepository<Integrations, Long>, JpaSpecificationExecutor {
  Optional<Integrations> findByWorkspaceId(Long workspaceId);
}
