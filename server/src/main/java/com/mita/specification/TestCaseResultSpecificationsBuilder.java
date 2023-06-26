

package com.mita.specification;

import com.mita.model.TestCaseResult;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class TestCaseResultSpecificationsBuilder extends BaseSpecificationsBuilder {

  private Specification<TestCaseResult> result;

  public TestCaseResultSpecificationsBuilder() {
    super(new ArrayList<>());
  }

  public Specification<TestCaseResult> build() {
    if (params.size() == 0) {
      return null;
    }

    result = new TestCaseResultSpecification(params.get(0));
    params.forEach((searchCriteria) -> result =
      Specification.where(result).and(new TestCaseResultSpecification(searchCriteria)));
    return result;
  }
}
