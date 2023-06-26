package com.mita.automator.actions.mobile.android.scroll;

import com.mita.automator.actions.mobile.MobileElementAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Rectangle;

import java.time.Duration;


public class ScrollInsideAnElementFromMiddleToBottomAction extends MobileElementAction {

  private static final String SUCCESS_MESSAGE = "Successfully scrolled inside an element from middle to bottom";

  @Override
  protected void execute() throws Exception {
    findElement();
    Rectangle rect = getElement().getRect();
    PointOption start = PointOption.point(rect.x + rect.width / 2, rect.y + rect.height / 2);
    PointOption end = PointOption.point(rect.x + rect.width / 2, rect.y + rect.height);
    TouchAction action = new TouchAction(getDriver());
    action.press(start).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5))).moveTo(end).release().perform();
    setSuccessMessage(SUCCESS_MESSAGE);
  }

}