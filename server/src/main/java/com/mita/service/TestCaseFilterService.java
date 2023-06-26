

package com.mita.service;

import com.mita.exception.ResourceNotFoundException;
import com.mita.repository.TestCaseFilterRepository;
import com.mita.model.TestCaseFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestCaseFilterService {

  private final TestCaseFilterRepository testCaseFilterRepository;

  public Page<TestCaseFilter> findAllVisible(Long versionId, Pageable pageable) {
    return testCaseFilterRepository.findAll(versionId, pageable);
  }

  public TestCaseFilter find(Long id) throws ResourceNotFoundException {
    return testCaseFilterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TestCaseFilter missing with" + id));
  }

  public TestCaseFilter create(TestCaseFilter testCaseFilter) {
    return testCaseFilterRepository.save(testCaseFilter);
  }

  public TestCaseFilter update(TestCaseFilter testCaseFilter) {
    return testCaseFilterRepository.save(testCaseFilter);
  }

  public void destroy(Long id) throws ResourceNotFoundException {
    TestCaseFilter testCaseFilter = find(id);
    testCaseFilterRepository.delete(testCaseFilter);
  }

}

