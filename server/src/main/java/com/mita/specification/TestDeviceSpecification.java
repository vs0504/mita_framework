

package com.mita.specification;

import com.mita.model.TestDevice;

import javax.persistence.criteria.*;

public class TestDeviceSpecification extends BaseSpecification<TestDevice> {
  public TestDeviceSpecification(final SearchCriteria criteria) {
    super(criteria);
  }


  @Override
  protected Expression<String> getPath(SearchCriteria criteria, Root<TestDevice> root) {
    String key = criteria.getKey();
    if (key.equals("entityType")) {
      Join s = root.join("testPlan", JoinType.INNER);
      return s.get(key);
    }
    return root.get(criteria.getKey());
  }

  public Predicate toPredicate(Root<TestDevice> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    return super.toPredicate(root, query, builder);
  }

  @Override
  protected Object getEnumValueIfEnum(String key, Object value, SearchOperation op) {
    if (key.equals("disable"))
      return Boolean.parseBoolean(value.toString());
    else
      return value;
  }
}
