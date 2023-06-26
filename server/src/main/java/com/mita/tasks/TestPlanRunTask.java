
package com.mita.tasks;

import com.mita.automator.AutomatorConfig;
import com.mita.automator.entity.TestDeviceEntity;
import com.mita.http.AssetsHttpClient;
import com.mita.http.WebAppHttpClient;
import com.mita.automator.exceptions.AutomatorException;
import com.mita.automator.executions.AbstractTestPlanRunTask;
import com.mita.automator.runners.ExecutionEnvironmentRunner;
import org.apache.logging.log4j.ThreadContext;

public class TestPlanRunTask extends AbstractTestPlanRunTask {

  public TestPlanRunTask(TestDeviceEntity testDeviceEntity) {
    super(testDeviceEntity, ThreadContext.get("X-Request-Id"), new WebAppHttpClient(), new AssetsHttpClient());
  }

  @Override
  public void execute() throws Exception {
    ExecutionEnvironmentRunner driver = new ExecutionEnvironmentRunner(environment, environmentRunResult,
      webHttpClient, assetsHttpClient);
    environmentRunResult = driver.run();
  }

  @Override
  public void afterExecute() throws AutomatorException {
    super.afterExecute();
    AutomatorConfig.getInstance().getAppBridge().postEnvironmentResult(environmentRunResult);
  }
}
