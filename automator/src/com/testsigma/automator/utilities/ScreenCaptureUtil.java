package com.testsigma.automator.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.testsigma.automator.constants.EnvSettingsConstants;
import com.testsigma.automator.constants.StorageConstants;
import com.testsigma.automator.entity.TestDeviceSettings;
import com.testsigma.automator.entity.UserRequestDto;
import com.testsigma.automator.exceptions.TestsigmaScreenShotException;
import com.testsigma.automator.runners.EnvironmentRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Log4j2
@RequiredArgsConstructor
public class ScreenCaptureUtil {
  public static String JPEG = "jpeg";

  public static String bearerTokenForPythonFile = "";


  public List<ObjectNode> screenshots;

  JsonNodeFactory jnf = JsonNodeFactory.instance;

  public ScreenCaptureUtil(List<ObjectNode> screenshots) {
    this.screenshots = screenshots;
  }


  public void takeScreenShot(WebDriver webdriver, String loalFolderPath, String relativePath) throws Exception {
    try {
      byte[] srcFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.BYTES);
      saveScreenshotFile(srcFile, loalFolderPath, relativePath);

    } catch (WebDriverException e) {
      log.debug("Exception in taking screenshot using WebDriver. Details :: " + e.getMessage());
      log.error(e.getMessage(), e);
      if (e instanceof UnhandledAlertException) {
        log.debug("The Exception is caused by Unhandled Alert.");
        takeScreenShot(loalFolderPath, relativePath);
      }
    } catch (Exception e) {
      log.debug("Exception while Tacking screenshot" + e);
      log.error(e.getMessage(), e);
    }
  }

  public String takeScreenShotAndSave(WebDriver webdriver) throws Exception {
    String screenShotHashcode = "";
    try {

     File file =  ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
    screenShotHashcode = saveScreenshotFileUsingPython(file);

    } catch (WebDriverException e) {
      log.debug("Exception in taking screenshot using WebDriver. Details :: " + e.getMessage());
      log.error(e.getMessage(), e);
      if (e instanceof UnhandledAlertException) {
        log.debug("The Exception is caused by Unhandled Alert.");
      }
    } catch (Exception e) {
      log.debug("Exception while Tacking screenshot" + e);
      log.error(e.getMessage(), e);
    }
    return screenShotHashcode;
  }

  private String saveScreenshotFileUsingPython(File scfile ) {
    RestTemplate restTemplate = new RestTemplate();
    String fileType = null;

    String fileKey = "";
    try {
      String url = "https://fileupload-test.machint.com/file-upload";
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.MULTIPART_FORM_DATA);
      headers.set("Authorization",bearerTokenForPythonFile);
      MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();

      formData.add("file", new FileSystemResource(scfile));
      formData.add("uploadedBy","Machint");
      formData.add("generatedDate",new Date());
      formData.add("status","1");
      formData.add("requestid","23576598");
      formData.add("requestsrc","UI");
      formData.add("requesttype","image");
      HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(formData,headers);
      Map response = restTemplate.postForObject(url, entity, Map.class);
      System.out.println("response--->"+response);

      ObjectMapper mapper = new ObjectMapper();
      JsonNode jsonNode = mapper.valueToTree(response).get("responsedata");
      fileKey = ""+jsonNode.get("file_name");

      return fileKey.substring(1, fileKey.length() - 1);

    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return fileKey;
    }


  }


  public void takeScreenShot(String localFolderPath, String relativePath) throws Exception {
    Integer SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit()
      .getScreenSize().getWidth();

    Integer SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit()
      .getScreenSize().getHeight();

    BufferedImage img = new Robot().createScreenCapture(new Rectangle(SCREEN_WIDTH, SCREEN_HEIGHT));
    saveScreenshotFile(img, localFolderPath, relativePath);
  }

  public void createScreenshotsFolder() {
    TestDeviceSettings settings = EnvironmentRunner.getRunnerEnvironmentEntity().getEnvSettings();
    Long envRunId = EnvironmentRunner.getRunnerEnvironmentRunResult().getId();
    StringBuffer screenshots = new StringBuffer(PathUtil.getInstance().getScreenshotsPath());
    File f = new File(PathUtil.getInstance().getScreenshotsPath());
    f.mkdirs();

    String path = screenshots.append(File.separator).append(envRunId).toString();
    settings.setScreenshotLocalPath(path);

  }

  public void deleteScreenshotsFolder(Map<String, String> settings) {
    String path = settings.get(EnvSettingsConstants.KEY_SCREENSHOT_LOCAL_PATH);
    try {
      FileUtils.forceDelete(new File(path));
    } catch (IOException e) {
      log.error("Exception while deleting files :: " + path, e);
    }
  }

  private void saveScreenshotFile(BufferedImage img, String localFolderPath, String relativePath) {
    try {
      String path = getLocalScreenShotPath(localFolderPath, relativePath);
      File f = new File(path);
      f.createNewFile();
      ImageIO.write(img, JPEG, f);
      saveScreenshotPaths(path, relativePath);
    } catch (IOException e) {
      log.error("Exception while saving screenshot :: " + relativePath, e);
    }
  }

  private void saveScreenshotFile(byte[] srcFile, String localFolderPath, String relativePath) {
    File f;
    try {
      String path = getLocalScreenShotPath(localFolderPath, relativePath);
      f = new File(path);
//      f.createNewFile();
      FileUtils.writeByteArrayToFile(f, srcFile);
      saveScreenshotPaths(path, relativePath);
    } catch (IOException e) {
      log.error("Exception while saving screenshot :: " + relativePath, e);
    }
  }

  private void saveScreenshotPaths(String srcFile, String relativePath) {
    ObjectNode image = jnf.objectNode();
    image.put(StorageConstants.LOCAL_FILE_PATH, srcFile);
    image.put(StorageConstants.STORAGE_FILE_PATH, relativePath);
    this.screenshots.add(image);
  }

  public void screenShotWithURL(String localFolderPath, String relativePath, WebDriver webdriver) throws TestsigmaScreenShotException {
    try {
      String url = webdriver.getCurrentUrl();
      String path = getLocalScreenShotPath(localFolderPath, relativePath);
      File file = new File(path);
      Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.simple()).takeScreenshot(webdriver);
      saveImageWithUrl(url, file, screenshot);
      saveScreenshotPaths(path, relativePath);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new TestsigmaScreenShotException("Unable to take ScreenShot.  Details :" + e.getMessage());
    }
  }

  public void fullPageScreenshotWithURL(String localFolderPath, String relativePath, WebDriver webdriver) throws TestsigmaScreenShotException {
    try {
      log.debug("Fetching url");
      String url = webdriver.getCurrentUrl();
      log.debug("Fetched url" + url);
      String path = getLocalScreenShotPath(localFolderPath, relativePath);
      log.debug("LocalScreenshot path" + path);
      File file = new File(path);
      log.debug("Before taking screenshot");
      Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(webdriver);
      log.debug("After taking screenshot");
      saveImageWithUrl(url, file, screenshot);
      log.debug("before saving image");
      saveScreenshotPaths(path, relativePath);
      log.debug("save screenshot path");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new TestsigmaScreenShotException("Unable to take ScreenShot.  Details :" + e.getMessage());
    }
  }

  private void saveImageWithUrl(String url, File file, Screenshot screenshot) {
    try {
      BufferedImage bufferedImage = ensureOpaque(screenshot.getImage());
      Graphics graphics = bufferedImage.getGraphics();
      graphics.setColor(Color.RED);
      graphics.setFont(new Font("Arial Black", Font.CENTER_BASELINE, 14));
      graphics.drawString(url, 20, 25);
      ImageIO.write(bufferedImage, JPEG, file);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  private BufferedImage ensureOpaque(BufferedImage bi) {
    if (bi.getTransparency() == BufferedImage.OPAQUE)
      return bi;
    int w = bi.getWidth();
    int h = bi.getHeight();
    int[] pixels = new int[w * h];
    bi.getRGB(0, 0, w, h, pixels, 0, w);
    BufferedImage bi2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    bi2.setRGB(0, 0, w, h, pixels, 0, w);
    return bi2;
  }

  private String getLocalScreenShotPath(String screenShotFolder, String s3PreSignedURL)
    throws MalformedURLException {
    String fileName = FilenameUtils.getName(new java.net.URL(s3PreSignedURL).getPath());
    return screenShotFolder + File.separator + fileName;
  }

  public static void  getBearerForFile() {
    RestTemplate  restTemplate = new RestTemplate();
    String url = "https://fileupload-test.machint.com/login";
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    UserRequestDto userRequestDto = new UserRequestDto();
    HttpEntity<Object> entity = new HttpEntity<>(userRequestDto,headers);
    Map<String, String> urlParams = new HashMap<>();
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
    String bearerTkoken = "Bearer " ;
    try {
      ResponseEntity<Map> response = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.valueOf("POST"), entity, Map .class);

      if (response.getBody() != null) {
        Map responseBody = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.valueToTree(responseBody).get("response");
        String bearer = "" + jsonNode.get("jwt_token");
        bearerTkoken = bearerTkoken+ bearer.substring(1, bearer.length() - 1);

      }


    } catch (Exception ex) {
      ex.printStackTrace();
    }
    bearerTokenForPythonFile = bearerTkoken;

  }

}

