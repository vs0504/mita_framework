package com.mita.automator.actions.web.ifconditional;

import com.mita.automator.actions.constants.ActionConstants;
import com.mita.automator.actions.ElementAction;
import com.mita.automator.actions.web.verify.VerifyAlertAbsenceAction;
import com.mita.automator.actions.web.verify.VerifyAlertPresenceAction;

import java.lang.reflect.InvocationTargetException;

public class AlertVisibilityStatusAction extends ElementAction {
  @Override
  public void execute() throws Exception {
    String status = getTestData();
    switch (status) {
      case ActionConstants.NOT_VISIBLE:
        VerifyAlertAbsenceAction absence = (VerifyAlertAbsenceAction) this.initializeChildSnippet(VerifyAlertAbsenceAction.class);
        absence.execute();
        this.setSuccessMessage(absence.getSuccessMessage());
        break;
      case ActionConstants.VISIBLE:
        VerifyAlertPresenceAction available = (VerifyAlertPresenceAction) this.initializeChildSnippet(VerifyAlertPresenceAction.class);
        available.execute();
        this.setSuccessMessage(available.getSuccessMessage());
        break;
    }
  }

  protected Object initializeChildSnippet(Class<?> snippetClassName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    ElementAction snippet = (ElementAction) snippetClassName.getDeclaredConstructor().newInstance();
    snippet.setDriver(this.getDriver());
    snippet.setElement(this.getElement());
    snippet.setElementPropertiesEntityMap(this.getElementPropertiesEntityMap());
    snippet.setTestDataPropertiesEntityMap(this.getTestDataPropertiesEntityMap());
    snippet.setAttributesMap(this.getAttributesMap());
    snippet.setRuntimeDataProvider(this.getRuntimeDataProvider());
    snippet.setEnvSettings(this.getEnvSettings());
    return snippet;

  }

}
