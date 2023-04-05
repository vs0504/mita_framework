package com.testsigma.automator.actions.web.generic;

import com.testsigma.automator.actions.ElementAction;
import com.testsigma.automator.utilities.ScreenCaptureUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Arrays;

import static com.testsigma.automator.constants.NaturalTextActionConstants.TEST_STEP_DATA_MAP_KEY_TEST_DATA;

public class UploadFileAction extends ElementAction {
  @Override
  protected void execute() throws Exception {
    findElement();
    String downloadURL = getTestDataPropertiesEntity(TEST_STEP_DATA_MAP_KEY_TEST_DATA).getTestDataValuePreSignedURL();
    String fileName = String.format("%s_%s", System.currentTimeMillis(), FilenameUtils.getName(new URL(downloadURL).getPath()));
    byte[] fileContent = getFileInBytes(fileName.split("_")[1]);

    String filePath = String.format("%s%s%s", FileUtils.getTempDirectoryPath(), File.separator, fileName);
    File file = new File(filePath);
    FileOutputStream fos = new FileOutputStream(file);
    fos.write(fileContent);
    fos.close();
    getElement().sendKeys(filePath);
    setSuccessMessage("Successfully executed.");
  }

  @NotNull
  private static byte[] getFileInBytes(String fileHashCode) {
    RestTemplate restTemplate = new RestTemplate();
    String fileUrl = "https://docs.machint.com/get-uploaded-file?file_name="+fileHashCode;
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
    ScreenCaptureUtil.getBearerForFile();
    String bearerToken= ScreenCaptureUtil.bearerTokenForPythonFile;
    headers.add("Authorization",bearerToken);
    HttpEntity<String> entity = new HttpEntity<>(headers);
    ResponseEntity<byte[]> response = restTemplate.exchange(
            fileUrl,
            HttpMethod.GET,
            entity,
            byte[].class);
    return response.getBody();
  }
}
