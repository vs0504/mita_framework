

package com.mita.controller.api.agent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mita.dto.IosWdaResponseDTO;
import com.mita.config.StorageServiceFactory;
import com.mita.config.URLConstants;
import com.mita.dto.IosXCTestResponseDTO;
import com.mita.model.StorageAccessLevel;
import com.mita.dto.AgentDeviceDTO;
import com.mita.dto.IosDeveloperImageDTO;
import com.mita.exception.ResourceNotFoundException;
import com.mita.exception.MitaDatabaseException;
import com.mita.exception.MitaException;
import com.mita.mapper.AgentDeviceMapper;
import com.mita.model.Agent;
import com.mita.model.AgentDevice;
import com.mita.model.ProvisioningProfileDevice;
import com.mita.service.*;
import com.mita.util.HttpClient;
import com.mita.util.HttpResponse;
import com.mita.web.request.AgentDeviceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@Log4j2
@RestController(value = "agentAgentDevicesController")
@RequestMapping(value = {"/api/agents/{agentUuid}/devices"})
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@CrossOrigin
public class AgentDevicesController {

  private final AgentDeviceService agentDeviceService;
  private final AgentDeviceMapper agentDeviceMapper;
  private final AgentService agentService;
  private final HttpClient httpClient;
  private final StorageServiceFactory storageServiceFactory;
  private final ProvisioningProfileDeviceService provisioningProfileDeviceService;
  private final TestsigmaOSConfigService testsigmaOSConfigService;

  private final ResignService resignWdaService;

  @RequestMapping(value = "/status", method = RequestMethod.PUT)
  public void syncInitialDeviceStatus(@PathVariable("agentUuid") String agentUuid) throws MitaDatabaseException,
    ResourceNotFoundException {
    log.info(String.format("Received a PUT request api/agents/%s/devices/status ", agentUuid));
    Agent agent = agentService.findByUniqueId(agentUuid);
    agentDeviceService.updateDevicesStatus(agent.getId());
  }

  @RequestMapping(value = "/{uniqueId}", method = RequestMethod.GET)
  public AgentDeviceDTO show(@PathVariable("agentUuid") String agentUuid, @PathVariable("uniqueId") String uniqueId)
    throws ResourceNotFoundException {
    log.info(String.format("Received a GET request api/agents/%s/devices/%s ", agentUuid, uniqueId));
    Agent agent = agentService.findByUniqueId(agentUuid);
    AgentDevice agentDevice = agentDeviceService.findAgentDeviceByUniqueId(agent.getId(), uniqueId);
    return agentDeviceMapper.map(agentDevice);
  }

  @RequestMapping(method = RequestMethod.POST)
  public AgentDeviceDTO create(@PathVariable("agentUuid") String agentUuid,
                               @RequestBody AgentDeviceRequest agentDeviceRequest)
    throws MitaDatabaseException, ResourceNotFoundException {
    log.info(String.format("Received a POST request api/agents/%s/devices . Request body is [%s]  ",
      agentUuid, agentDeviceRequest));
    Agent agent = agentService.findByUniqueId(agentUuid);
    AgentDevice agentDevice = agentDeviceMapper.map(agentDeviceRequest);
    agentDevice.setAgentId(agent.getId());
    agentDevice = agentDeviceService.create(agentDevice);
    return agentDeviceMapper.map(agentDevice);
  }

  @RequestMapping(value = "/{uniqueId}", method = RequestMethod.PUT)
  public AgentDeviceDTO update(@PathVariable("agentUuid") String agentUuid,
                               @PathVariable("uniqueId") String uniqueId,
                               @RequestBody AgentDeviceRequest agentDeviceRequest)
    throws MitaDatabaseException, ResourceNotFoundException {
    log.info(String.format("Received a PUT request api/agents/%s/devices/%s . Request body is [%s]  ",
      agentUuid, uniqueId, agentDeviceRequest));
    Agent agent = agentService.findByUniqueId(agentUuid);
    AgentDevice agentDevice = agentDeviceService.findAgentDeviceByUniqueId(agent.getId(), uniqueId);
    agentDeviceMapper.map(agentDeviceRequest, agentDevice);
    agentDevice = agentDeviceService.update(agentDevice);
    return agentDeviceMapper.map(agentDevice);
  }

  @RequestMapping(value = "/{uniqueId}", method = RequestMethod.DELETE)
  public AgentDeviceDTO delete(@PathVariable("agentUuid") String agentUuid,
                               @PathVariable("uniqueId") String uniqueId)
    throws MitaDatabaseException, ResourceNotFoundException {
    log.info(String.format("Received a DELETE request api/agents/%s/devices/%s", agentUuid, uniqueId));
    Agent agent = agentService.findByUniqueId(agentUuid);
    AgentDevice agentDevice = agentDeviceService.findAgentDeviceByUniqueId(agent.getId(), uniqueId);
    agentDeviceService.destroy(agentDevice);
    return agentDeviceMapper.map(agentDevice);
  }

  @RequestMapping(value = "/developer/{osVersion}/", method = RequestMethod.GET)
  public IosDeveloperImageDTO developer(@PathVariable("agentUuid") String agentUuid,
                                        @PathVariable("osVersion") String deviceOsVersion) throws MitaException {
    log.info(String.format("Received a GET request api/agents/%s/devices/developer/%s", agentUuid, deviceOsVersion));
    HttpResponse<IosDeveloperImageDTO> response = httpClient.get(testsigmaOSConfigService.getUrl() +
      URLConstants.TESTSIGMA_OS_PUBLIC_IOS_IMAGE_FILES_URL + "/" + deviceOsVersion, getHeaders(), new TypeReference<>() {
    });

    IosDeveloperImageDTO iosDeveloperImageDTO = response.getResponseEntity();
    log.info("Ios developer image url DTO - " + iosDeveloperImageDTO);
    return iosDeveloperImageDTO;
  }

  @RequestMapping(value = "/{deviceUuid}/wda_real_device", method = RequestMethod.GET)
  public IosWdaResponseDTO deviceWdaUrl(@PathVariable String agentUuid, @PathVariable String deviceUuid)
          throws MitaException, MalformedURLException {
    log.info(String.format("Received a GET request api/agents/%s/devices/%s/wda", agentUuid, deviceUuid));
    IosWdaResponseDTO iosWdaResponseDTO = new IosWdaResponseDTO();
    Agent agent = agentService.findByUniqueId(agentUuid);
    AgentDevice agentDevice = agentDeviceService.findAgentDeviceByUniqueId(agent.getId(), deviceUuid);
    String presignedUrl;
    ProvisioningProfileDevice profileDevice = provisioningProfileDeviceService.findByAgentDeviceId(agentDevice.getId());
    if(profileDevice == null) {
      throw new MitaException("could not find a provisioning profile for this device. Unable to fetch WDA");
    }
    presignedUrl = storageServiceFactory.getStorageService().generatePreSignedURL("wda/"
            + profileDevice.getProvisioningProfileId() + "/wda.ipa", StorageAccessLevel.READ, 180).toString();
    iosWdaResponseDTO.setWdaPresignedUrl(presignedUrl);
    log.info("Ios Wda Response DTO - " + iosWdaResponseDTO);
    return iosWdaResponseDTO;
  }

  @RequestMapping(value = "/wda_emulator", method = RequestMethod.GET)
  public IosWdaResponseDTO deviceWdaEmulatorUrl(@PathVariable String agentUuid)
          throws MitaException, MalformedURLException {
    log.info(String.format("Received a GET request api/agents/%s/devices/wda_emulator", agentUuid));
    IosWdaResponseDTO iosWdaResponseDTO = new IosWdaResponseDTO();

    ArrayList<Header> headers = new ArrayList<>();
    headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
    HttpResponse<String> response = httpClient.get(testsigmaOSConfigService.getUrl() +
            URLConstants.TESTSIGMA_OS_PUBLIC_WDA_EMULATOR_URL, headers, new TypeReference<>() {
    });
    URL wdaEmulatorRemoteURL = new URL(response.getResponseEntity());
    log.info("Received wda emulator remote url from proxy service: " + wdaEmulatorRemoteURL);
    iosWdaResponseDTO.setWdaPresignedUrl(wdaEmulatorRemoteURL.toString());
    log.info("Ios Wda Response DTO - " + iosWdaResponseDTO);
    return iosWdaResponseDTO;
  }

    @RequestMapping(value = "/xctest", method = RequestMethod.GET)
  public IosXCTestResponseDTO deviceXCTestLocalPath(@PathVariable String agentUuid)
            throws IOException, MitaException {
    log.info(String.format("Received a GET request api/agents/%s/devices/xctest", agentUuid));
    ArrayList<Header> headers = new ArrayList<>();
    headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
    HttpResponse<String> response = httpClient.get(testsigmaOSConfigService.getUrl() +
            URLConstants.TESTSIGMA_OS_PUBLIC_XCTEST_URL, headers, new TypeReference<>() {
    });
    URL xcTestRemoteURL = new URL(response.getResponseEntity());
    log.info("Received xctest remote url from proxy service: " + xcTestRemoteURL);
    IosXCTestResponseDTO iosXCTestResponseDTO = new IosXCTestResponseDTO();
    iosXCTestResponseDTO.setXcTestRemoteUrl(xcTestRemoteURL.toString());
    log.info("Ios XCTest Response DTO - " + iosXCTestResponseDTO);
    return iosXCTestResponseDTO;
  }

  private ArrayList<Header> getHeaders() {
    ArrayList<Header> headers = new ArrayList<>();
    headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
    return headers;
  }
}
