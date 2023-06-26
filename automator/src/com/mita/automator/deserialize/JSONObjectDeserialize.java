
package com.mita.automator.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.json.JSONObject;

import java.io.IOException;

public class JSONObjectDeserialize extends JsonDeserializer<JSONObject> {
  @Override
  public JSONObject deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
    return new JSONObject(treeNode.toString());
  }
}