package com.mita.automator.utilities;

import com.mita.automator.AutomatorConfig;
import com.mita.automator.entity.RuntimeEntity;
import com.mita.automator.exceptions.AutomatorException;
import com.mita.automator.drivers.DriverManager;
import com.mita.automator.runners.EnvironmentRunner;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 * This class help the user create a singleton instance of runtime data, which is re-used per
 * testSuite, per Execution-session to store and access runtime data across various classes and
 * functions.
 */

@Log4j2
@Data
public class RuntimeDataProvider {


  public RuntimeDataProvider() {
  }


  /**
   * Clear run time data for a webdriver session mapped to an execution ID.
   */
  public void clearRunTimeData(String executionID) {
    //TODO: remove from database
  }

  public String getRuntimeData(String variableName)
    throws AutomatorException {
    return AutomatorConfig.getInstance().getAppBridge().getRunTimeData(variableName, EnvironmentRunner.getRunnerEnvironmentRunResult().getId(),
      DriverManager.getDriverManager().getOngoingSessionId());
  }

  public void storeRuntimeVariable(String variableName, String value)
    throws AutomatorException {

    RuntimeEntity entity = new RuntimeEntity();
    entity.setName(variableName);
    entity.setValue(value);
    entity.setSessionId(DriverManager.getDriverManager().getOngoingSessionId());
    AutomatorConfig.getInstance().getAppBridge().updateRunTimeData(EnvironmentRunner.getRunnerEnvironmentRunResult().getId(), entity);
  }
}
