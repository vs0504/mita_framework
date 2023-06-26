

package com.mita.service;

import com.mita.specification.BaseSpecificationsBuilder;
import com.mita.specification.ElementScreenSpecification;
import com.mita.model.ElementScreenName;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class ElementScreenNameSpecificationsBuilder extends BaseSpecificationsBuilder {

  private Specification<ElementScreenName> result;

  public ElementScreenNameSpecificationsBuilder() {
    super(new ArrayList<>());
  }

  public Specification<ElementScreenName> build() {
    if (params.size() == 0) {
      return null;
    }

    result = new ElementScreenSpecification(params.get(0));
    params.forEach((searchCriteria) -> result =
      Specification.where(result).and(new ElementScreenSpecification(searchCriteria)));
    return result;
  }

}
