

package com.mita.specification;

import com.mita.model.ScheduleTestPlan;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class ScheduleTestPlanSpecificationsBuilder extends BaseSpecificationsBuilder {

  private Specification<ScheduleTestPlan> result;

  public ScheduleTestPlanSpecificationsBuilder() {
    super(new ArrayList<>());
  }

  public Specification<ScheduleTestPlan> build() {
    if (params.size() == 0) {
      return null;
    }

    result = new ScheduleTestPlanSpecification(params.get(0));
    params.forEach((searchCriteria) -> result =
      Specification.where(result).and(new ScheduleTestPlanSpecification(searchCriteria)));
    return result;
  }
}
