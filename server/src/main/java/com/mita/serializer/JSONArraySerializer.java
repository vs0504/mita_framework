

package com.mita.serializer;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.json.JSONArray;

import java.io.IOException;

public class JSONArraySerializer extends JsonSerializer<JSONArray> {

  @Override
  public void serialize(JSONArray jsonObject, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
    throws IOException {
    jsonGenerator.writeRawValue(jsonObject.toString());
  }
}
