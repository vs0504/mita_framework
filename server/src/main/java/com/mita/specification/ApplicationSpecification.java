package com.mita.specification;

import com.mita.model.Workspace;
import com.mita.model.WorkspaceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationSpecification extends BaseSpecification<Workspace> {

  public ApplicationSpecification(final SearchCriteria criteria) {
    super(criteria);
  }

  @Override
  protected Object getEnumValueIfEnum(String key, Object value, SearchOperation op) {
    switch (key) {
      case "workspaceType":
        if (op == SearchOperation.IN) {
          if (value.getClass().getName().equals("java.lang.String")) {
            List<WorkspaceType> types = new ArrayList<>();
            Arrays.asList(value.toString().split("#")).forEach(string -> {
              types.add(WorkspaceType.valueOf(string));
            });
            return types;
          } else {
            return value;
          }
        }
        return WorkspaceType.valueOf(value.toString());
      case "isDemo":
        return Boolean.parseBoolean(value.toString());
      default:
        return super.getEnumValueIfEnum(key, value, op);
    }
  }
}
