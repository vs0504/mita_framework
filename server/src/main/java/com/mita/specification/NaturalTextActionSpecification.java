

package com.mita.specification;

import com.mita.model.NaturalTextActions;
import com.mita.model.StepActionType;
import com.mita.model.WorkspaceType;

public class NaturalTextActionSpecification extends BaseSpecification<NaturalTextActions> {

  public NaturalTextActionSpecification(final SearchCriteria criteria) {
    super(criteria);
  }

  @Override
  protected Object getEnumValueIfEnum(String key, Object value, SearchOperation op) {
    switch (key) {
      case "workspaceType":
        return WorkspaceType.valueOf(value.toString());
      case "deprecated":
        return Boolean.parseBoolean(value.toString());
      case "stepActionType":
        return StepActionType.valueOf(value.toString());
      default:
        return value;
    }
  }
}
