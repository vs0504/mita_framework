

package com.mita.service;

import com.mita.repository.TestCaseDataDrivenResultRepository;
import com.mita.mapper.TestCaseDataDrivenResultMapper;
import com.mita.model.ResultConstant;
import com.mita.model.TestCaseDataDrivenResult;
import com.mita.model.TestDataSet;
import com.mita.web.request.TestCaseResultRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestCaseDataDrivenResultService {

  private final TestCaseDataDrivenResultRepository testCaseDataDrivenResultRepository;
  private final TestCaseDataDrivenResultMapper testCaseDataDrivenResultMapper;

  public TestCaseDataDrivenResult find(Long id) {
    return testCaseDataDrivenResultRepository.findById(id).orElse(null);
  }

  public List<TestCaseDataDrivenResult> findByTestCaseResultIdAndResultNot(Long testCaseResultId, ResultConstant result){
    return testCaseDataDrivenResultRepository.findByTestCaseResultIdAndResultNot(testCaseResultId, result);
  }

  public List<TestCaseDataDrivenResult> findAllByTestCaseResultId(Long testCaseResultId){
    return testCaseDataDrivenResultRepository.findAllByTestCaseResultId(testCaseResultId);
  }

  public void deleteByIterationResultId(Long deleteByTestCaseResultId) {
    testCaseDataDrivenResultRepository.deleteByIterationResultId(deleteByTestCaseResultId);
  }

  public TestCaseDataDrivenResult create(TestCaseResultRequest testCaseResultRequest, TestDataSet testDataSet) {
    TestCaseDataDrivenResult testCaseDataDrivenResult = testCaseDataDrivenResultMapper.map(testCaseResultRequest);
    testCaseDataDrivenResult.setTestData(new ObjectMapperService().convertToJson(testDataSet));
    return testCaseDataDrivenResultRepository.save(testCaseDataDrivenResult);
  }


  public Page<TestCaseDataDrivenResult> findAll(Specification<TestCaseDataDrivenResult> spec, Pageable pageable) {
    return testCaseDataDrivenResultRepository.findAll(spec, pageable);
  }

  public TestCaseDataDrivenResult create(TestCaseDataDrivenResult testCaseDataDrivenResult) {
    return this.testCaseDataDrivenResultRepository.save(testCaseDataDrivenResult);
  }
}
