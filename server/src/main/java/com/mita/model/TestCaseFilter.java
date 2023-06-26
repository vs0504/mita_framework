

package com.mita.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TEST_CASE")
public class TestCaseFilter extends ListFilter {
}
