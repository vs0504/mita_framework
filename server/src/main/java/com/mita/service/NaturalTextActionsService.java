

package com.mita.service;


import com.mita.exception.ResourceNotFoundException;
import com.mita.repository.NaturalTextActionsRepository;
import com.mita.model.NaturalTextActions;
import com.mita.model.WorkspaceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "naturalTextActionsService")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NaturalTextActionsService {
  private final NaturalTextActionsRepository naturalTextActionsRepository;

  public Page<NaturalTextActions> findAll(Specification<NaturalTextActions> spec, Pageable pageable) {
    return naturalTextActionsRepository.findAll(spec, pageable);
  }

  public Page<NaturalTextActions> findAllByWorkspaceType(WorkspaceType workspaceType, Pageable pageable) {
    return naturalTextActionsRepository.findAllByWorkspaceType(workspaceType, pageable);
  }

  public NaturalTextActions findById(Long naturalTextActionId) throws ResourceNotFoundException {
    return this.naturalTextActionsRepository.findById(naturalTextActionId).orElseThrow(() -> new ResourceNotFoundException("NaturalTextAction missing::" + naturalTextActionId));
  }

  public List<NaturalTextActions> findByDisplayName(String displayName) {
    return this.naturalTextActionsRepository.findAllByDisplayName(displayName);
  }

  public List<NaturalTextActions> findAllByAction(String action) {
    return this.naturalTextActionsRepository.findAllByAction(action);
  }

}
