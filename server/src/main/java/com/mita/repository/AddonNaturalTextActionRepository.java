
package com.mita.repository;

import com.mita.model.AddonNaturalTextAction;
import com.mita.model.WorkspaceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AddonNaturalTextActionRepository extends BaseRepository<AddonNaturalTextAction, Long> {
  Optional<AddonNaturalTextAction> findByAddonIdAndFullyQualifiedName(Long addonId, String fullyQualifiedName);

  List<AddonNaturalTextAction> findAllByAddonId(Long addonId);

  Page<AddonNaturalTextAction> findAllByWorkspaceType(WorkspaceType workspaceType, Pageable pageable);
}
