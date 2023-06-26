

package com.mita.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("STEP_GROUP")
public class StepGroupFilter extends ListFilter {
}
