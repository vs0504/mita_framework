package com.mita.repository;

import com.mita.model.AdhocRunConfiguration;
import com.mita.model.WorkspaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AdhocRunConfigurationRepository extends JpaRepository<AdhocRunConfiguration, Long> {
  List<AdhocRunConfiguration> findAllByWorkspaceType(WorkspaceType workspaceType);
}
