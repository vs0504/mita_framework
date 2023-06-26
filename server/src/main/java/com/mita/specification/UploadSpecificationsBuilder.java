

package com.mita.specification;

import com.mita.model.Upload;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class UploadSpecificationsBuilder extends BaseSpecificationsBuilder {

  private Specification<Upload> result;

  public UploadSpecificationsBuilder() {
    super(new ArrayList<>());
  }

  public Specification<Upload> build() {
    if (params.size() == 0) {
      return null;
    }

    result = new UploadSpecification(params.get(0));
    for (SearchCriteria searchCriteria : params) {
      if (!searchCriteria.getKey().equals("deviceId")) {
        result = Specification.where(result).and(new UploadSpecification(searchCriteria));
      }
    }
    return result;
  }
}

