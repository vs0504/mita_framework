package com.mita.repository;


import com.mita.model.AddonPluginTestDataFunctionParameter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AddonPluginTestDataFunctionParameterRepository extends BaseRepository<AddonPluginTestDataFunctionParameter, Long> {

  void deleteAllByTestDataFunctionId(Long testDataFunctionId);

  List<AddonPluginTestDataFunctionParameter> findByTestDataFunctionId(Long functionId);
}
