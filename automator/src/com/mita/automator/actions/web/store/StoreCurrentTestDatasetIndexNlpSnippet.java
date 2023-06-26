package com.mita.automator.actions.web.store;


import com.mita.automator.exceptions.AutomatorException;
import com.mita.automator.actions.ElementAction;

public class StoreCurrentTestDatasetIndexNlpSnippet extends ElementAction {
  private static final String SUCCESS_MESSAGE = "Successfully test data index is saved into run time variable<br><b>%s</b>";
  private static final String ERROR_MESSAGE = "Invalid TestData Index <b>%s</b>";
  private static final String ERROR_TEST_CASE_MESSAGE = "Test data profile is not associated";
  @Override
  protected void execute() throws Exception {
    Integer testDataIndex = this.getTestDataProfileEntity().getTestDataIndex();
    if(this.getTestDataProfileEntity().getTestDataSetName() == null){
      throw new AutomatorException(String.format(ERROR_TEST_CASE_MESSAGE));
    }
    if(testDataIndex == null ||  testDataIndex <0){
      throw new AutomatorException(String.format(ERROR_MESSAGE, testDataIndex!= null ? testDataIndex+1 : null));
    }
    testDataIndex = testDataIndex+1;
    runtimeDataProvider.storeRuntimeVariable(getAttribute(), testDataIndex.toString());
    resultMetadata.put(getAttribute(), testDataIndex.toString());
    setSuccessMessage(String.format(SUCCESS_MESSAGE, testDataIndex.toString()));
  }
}
