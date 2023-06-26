
package com.mita.repository;

import com.mita.model.AddonPluginTestDataFunction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AddonPluginTestDataFunctionRepository extends BaseRepository<AddonPluginTestDataFunction, Long> {
  Optional<AddonPluginTestDataFunction> findByAddonIdAndFullyQualifiedName(Long addonId, String fullyQualifiedName);

  List<AddonPluginTestDataFunction> findAllByAddonId(Long addonId);
}
