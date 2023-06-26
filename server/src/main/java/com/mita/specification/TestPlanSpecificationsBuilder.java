

package com.mita.specification;

import com.mita.model.TestPlan;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class TestPlanSpecificationsBuilder extends BaseSpecificationsBuilder {

  private Specification<TestPlan> result;

  public TestPlanSpecificationsBuilder() {
    super(new ArrayList<>());
  }

  public Specification<TestPlan> build() {
    if (params.size() == 0) {
      return null;
    }

    result = new TestPlanSpecification(params.get(0));
    params.forEach((searchCriteria) -> result =
      Specification.where(result).and(new TestPlanSpecification(searchCriteria)));
    return result;
  }
}
