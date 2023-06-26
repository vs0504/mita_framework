

package com.mita.specification;

import com.mita.model.TestCaseDataDrivenResult;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class TestCaseDataDrivenResultSpecificationsBuilder extends BaseSpecificationsBuilder {

  private Specification<TestCaseDataDrivenResult> result;

  public TestCaseDataDrivenResultSpecificationsBuilder() {
    super(new ArrayList<>());
  }

  public Specification<TestCaseDataDrivenResult> build() {
    if (params.size() == 0) {
      return null;
    }

    result = new TestCaseDataDrivenResultSpecification(params.get(0));
    params.forEach((searchCriteria) -> result =
      Specification.where(result).and(new TestCaseDataDrivenResultSpecification(searchCriteria)));
    return result;
  }
}
