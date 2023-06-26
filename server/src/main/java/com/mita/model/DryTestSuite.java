

package com.mita.model;

import lombok.Data;
import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;


@Entity
@Data
@DiscriminatorFormula("case when entity_type is null then 'ADHOC_TEST_SUITE' else entity_type end")
@DiscriminatorValue("ADHOC_TEST_SUITE")
public class DryTestSuite extends AbstractTestSuite {
  @Transient
  private Long testCaseId;
}
