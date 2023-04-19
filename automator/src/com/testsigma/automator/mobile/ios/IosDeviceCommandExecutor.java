package com.testsigma.automator.mobile.ios;

import com.testsigma.automator.exceptions.AutomatorException;
import com.testsigma.automator.exceptions.TestsigmaException;
import com.testsigma.automator.utilities.PathUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Log4j2
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IosDeviceCommandExecutor {

  private static final String IDB_EXECUTABLE = "idb";
  private static final String XCRUN_EXECUTABLE = "xcrun";

  public String getTiDeviceExecutablePath() {
    if (SystemUtils.IS_OS_WINDOWS) {
      return PathUtil.getInstance().getIosPath() + File.separator + "tidevice.exe";
    } else {
      return PathUtil.getInstance().getIosPath() + File.separator + "tidevice";
    }
  }

  public String getXrunExecutablePath() throws TestsigmaException {
    if (SystemUtils.IS_OS_WINDOWS) {
      throw new TestsigmaException("Idb is not supported for Windows platform");
    } else {
      return XCRUN_EXECUTABLE;
    }
  }

  public Process runDeviceCommand(String[] subCommand, Boolean executeWithTiDevice) throws AutomatorException {
    try {
      String[] iosDeviceExecutablePath = new String[]{getXrunExecutablePath(), "simctl"};
      if(executeWithTiDevice) {
        iosDeviceExecutablePath = new String[]{getTiDeviceExecutablePath()};
      }
      String[] command = ArrayUtils.addAll(iosDeviceExecutablePath, subCommand);

      log.debug("Running the command - " + Arrays.toString(command));

      ProcessBuilder processBuilder = new ProcessBuilder(command);
      Process process = processBuilder.start();
      process.waitFor(10, TimeUnit.SECONDS);
      return process;
    } catch (Exception e) {
      throw new AutomatorException(e.getMessage());
    }
  }

  public String getProcessStreamResponse(Process p) throws AutomatorException {
    return getProcessStreamResponse(p, false);
  }

  public String getProcessStreamResponse(Process p, Boolean logOutput) throws AutomatorException {
    try {
      String stdOut = IOUtils.toString(p.getInputStream(), StandardCharsets.UTF_8);
      String stdError = IOUtils.toString(p.getErrorStream(), StandardCharsets.UTF_8);
      StringBuilder sb = new StringBuilder();
      sb.append(stdOut);
      sb.append(stdError);
     if (logOutput) {
        log.debug("Command output - " + sb);
      }
      return sb.toString();
    } catch (Exception e) {
      throw new AutomatorException(e.getMessage());
    }
  }
}
