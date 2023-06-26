

package com.mita.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ELEMENT")
@Data
public class ElementFilter extends ListFilter {
}
