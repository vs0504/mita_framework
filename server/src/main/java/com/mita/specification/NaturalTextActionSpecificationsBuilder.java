

package com.mita.specification;

import com.mita.model.NaturalTextActions;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class NaturalTextActionSpecificationsBuilder extends BaseSpecificationsBuilder {

  private Specification<NaturalTextActions> result;

  public NaturalTextActionSpecificationsBuilder() {
    super(new ArrayList<>());
  }

  public Specification<NaturalTextActions> build() {
    if (params.size() == 0) {
      return null;
    }

    result = new NaturalTextActionSpecification(params.get(0));
    params.forEach((searchCriteria) -> result =
      Specification.where(result).and(new NaturalTextActionSpecification(searchCriteria)));
    return result;
  }
}
