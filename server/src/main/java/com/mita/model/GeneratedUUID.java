
package com.mita.model;

import org.hibernate.annotations.ValueGenerationType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author PratheepV
 */
@ValueGenerationType(generatedBy = UUIDValueGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface GeneratedUUID {
  boolean useNonJdkImplementation() default true;

  String prefix() default "";

  boolean base64() default false;
}
