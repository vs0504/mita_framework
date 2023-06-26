

package com.mita.specification;

import com.mita.model.UploadType;
import com.mita.model.UploadVersion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UploadVersionSpecification extends BaseSpecification<UploadVersion> {
  public UploadVersionSpecification(final SearchCriteria criteria) {
    super(criteria);
  }

  @Override
  protected Object getEnumValueIfEnum(String key, Object value, SearchOperation op) {
    switch (key) {
      case "uploadType":
        if (op == SearchOperation.IN) {
          if (value.getClass().getName().equals("java.lang.String")) {
            List<UploadType> uploadTypes = new ArrayList<>();
            Arrays.asList(value.toString().split("#")).forEach(string -> {
              uploadTypes.add(UploadType.valueOf(string));
            });
            return uploadTypes;
          } else {
            return value;
          }
        }
        return UploadType.valueOf(value.toString());
      default:
        return value;
    }
  }
}