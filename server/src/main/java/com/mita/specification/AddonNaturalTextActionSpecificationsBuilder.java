

package com.mita.specification;

import com.mita.model.AddonNaturalTextAction;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class AddonNaturalTextActionSpecificationsBuilder extends BaseSpecificationsBuilder {

  private Specification<AddonNaturalTextAction> result;

  public AddonNaturalTextActionSpecificationsBuilder() {
    super(new ArrayList<>());
  }

  public Specification<AddonNaturalTextAction> build() {
    if (params.size() == 0) {
      return null;
    }

    result = new AddonNaturalTextActionSpecification(params.get(0));
    params.forEach((searchCriteria) -> result =
      Specification.where(result).and(new AddonNaturalTextActionSpecification(searchCriteria)));
    return result;
  }
}

