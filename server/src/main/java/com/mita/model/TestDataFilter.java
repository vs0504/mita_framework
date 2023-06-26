

package com.mita.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TEST_DATA")
@Data
public class TestDataFilter extends ListFilter {
}
