

package com.mita.converter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class StringSetConverter extends SetConverter<String> {
}
