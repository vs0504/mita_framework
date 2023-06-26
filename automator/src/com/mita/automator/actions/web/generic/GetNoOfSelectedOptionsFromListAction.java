package com.mita.automator.actions.web.generic;

import com.mita.automator.actions.ElementAction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class GetNoOfSelectedOptionsFromListAction extends ElementAction {
  private static final String SUCCESS_MESSAGE_WITH_DATA = "No of selected options::<b>%s</b>";

  @Override
  protected void execute() throws Exception {
    findElement();
    Select selElement = new Select(getElement());
    List<WebElement> options = selElement.getAllSelectedOptions();
    setSuccessMessage(String.format(SUCCESS_MESSAGE_WITH_DATA, options.size()));
  }
}
