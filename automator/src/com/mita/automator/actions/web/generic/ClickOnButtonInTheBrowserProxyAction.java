package com.mita.automator.actions.web.generic;

import com.mita.automator.actions.common.NavigateBackAction;
import com.mita.automator.actions.common.NavigateForwardAction;
import com.mita.automator.actions.constants.ActionConstants;
import com.mita.automator.exceptions.AutomatorException;
import com.mita.automator.actions.DriverAction;
import com.mita.automator.actions.ElementAction;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationTargetException;

@Log4j2
public class ClickOnButtonInTheBrowserProxyAction extends ElementAction {
    @Override
    public void execute() throws Exception {
      String button = getTestData();
      switch (button) {
        case ActionConstants.REFRESH:
          ReLoadCurrentPageAction refresh = (ReLoadCurrentPageAction) this.initializeChildSnippet(ReLoadCurrentPageAction.class);
          refresh.execute();
          this.setSuccessMessage(refresh.getSuccessMessage());
          break;
        case ActionConstants.BACK:
          NavigateBackAction back = (NavigateBackAction) this.initializeChildSnippet(NavigateBackAction.class);
          back.execute();
          this.setSuccessMessage(back.getSuccessMessage());
          break;
        case ActionConstants.FORWARD:
          NavigateForwardAction forward = (NavigateForwardAction) this.initializeChildSnippet(NavigateForwardAction.class);
          forward.execute();
          this.setSuccessMessage(forward.getSuccessMessage());
          break;
        default:
          setErrorMessage("Unable to Click on Button in the Browser due to error at test data");
          throw new AutomatorException("Unable to Click on Button in the Browser due to error at test data");
      }
    }
  protected Object initializeChildSnippet(Class<?> snippetClassName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    DriverAction snippet = (DriverAction) snippetClassName.getDeclaredConstructor().newInstance();
    snippet.setDriver(this.getDriver());
    return snippet;

  }
  }
