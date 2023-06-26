package com.mita.service;

import com.mita.exception.ResourceNotFoundException;
import com.mita.repository.AdhocRunConfigurationRepository;
import com.mita.mapper.AdhocRunConfigurationsMapper;
import com.mita.model.AdhocRunConfiguration;
import com.mita.model.WorkspaceType;
import com.mita.web.request.AdhocRunConfigurationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
public class AdhocRunConfigurationService {
  private final AdhocRunConfigurationRepository adhocRunConfigurationRepository;
  private final AdhocRunConfigurationsMapper mapper;

  public AdhocRunConfiguration create(AdhocRunConfigurationRequest configuration) {
    AdhocRunConfiguration config = mapper.map(configuration);
    return adhocRunConfigurationRepository.save(config);
  }

  public AdhocRunConfiguration update(AdhocRunConfiguration configuration) {
    return adhocRunConfigurationRepository.save(configuration);
  }

  public List<AdhocRunConfiguration> getDryRunConfigListByAppType(WorkspaceType appType) {
    List<AdhocRunConfiguration> getDryRunConfigList = adhocRunConfigurationRepository.findAllByWorkspaceType(appType);
    return getDryRunConfigList;
  }

  public void delete(AdhocRunConfiguration adhocRunConfiguration) {
    adhocRunConfigurationRepository.delete(adhocRunConfiguration);
  }

  public AdhocRunConfiguration find(Long id) throws ResourceNotFoundException {
    return this.adhocRunConfigurationRepository
      .findById(id).orElseThrow(() -> new ResourceNotFoundException("AdhocRunConfiguration missing with id" + id));
  }
}
