

package com.mita.specification;

import com.mita.model.TestDeviceResult;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class TestDeviceResultSpecificationsBuilder extends BaseSpecificationsBuilder {

  private Specification<TestDeviceResult> result;

  public TestDeviceResultSpecificationsBuilder() {
    super(new ArrayList<>());
  }

  public Specification<TestDeviceResult> build() {
    if (params.size() == 0) {
      return null;
    }

    result = new TestDeviceResultSpecification(params.get(0));
    params.forEach((searchCriteria) -> result =
      Specification.where(result).and(new TestDeviceResultSpecification(searchCriteria)));
    return result;
  }
}
