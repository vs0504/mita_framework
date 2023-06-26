

package com.mita.service;

import com.mita.exception.ResourceNotFoundException;
import com.mita.repository.NaturalTextActionExamplesRepository;
import com.mita.model.NaturalTextActionExample;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class NaturalTextActionExampleService {

  private final NaturalTextActionExamplesRepository repository;

  public NaturalTextActionExample findByNaturalTextActionId(Long naturalTextActionId) throws ResourceNotFoundException {
    return this.repository.findByNaturalTextActionId(naturalTextActionId).orElseThrow(() -> new ResourceNotFoundException("Example missing"));
  }

}

