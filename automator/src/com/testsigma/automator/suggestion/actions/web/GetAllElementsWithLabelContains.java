package com.testsigma.automator.suggestion.actions.web;

import com.testsigma.automator.suggestion.actions.SuggestionAction;
import com.testsigma.automator.suggestion.actions.SuggestionActionResult;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllElementsWithLabelContains extends SuggestionAction {
  @Override
  protected void execute() throws Exception {
    List<WebElement> elementsWithLabels = driver.findElements(By.xpath("//*[preceding-sibling::label[contains(text(),'" + testCaseStepEntity.getTestDataValue() + "')]]"));
    Assert.isTrue((elementsWithLabels.size() != 0), String.valueOf(SuggestionActionResult.Failure));
    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    for (WebElement webelement : elementsWithLabels) {
      Map<String, String> str = new HashMap<>();
      str.put("tag", webelement.getText());
      list.add(str);
    }
    engineResult.getMetaData().setSuggestions(new JSONObject().put("list", list));
  }
}
