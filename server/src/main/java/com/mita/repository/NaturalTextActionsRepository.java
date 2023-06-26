

package com.mita.repository;

import com.mita.model.WorkspaceType;
import com.mita.model.NaturalTextActions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface NaturalTextActionsRepository extends JpaRepository<NaturalTextActions, Long> {

  Page<NaturalTextActions> findAll(Specification<NaturalTextActions> spec, Pageable pageable);

  List<NaturalTextActions> findAllByDisplayName(String displayName);

  List<NaturalTextActions> findAllByAction(String action);

  Page<NaturalTextActions> findAllByWorkspaceType(WorkspaceType workspaceType, Pageable pageable);

}
