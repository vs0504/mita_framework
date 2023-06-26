package com.mita.controller;

import com.mita.config.StorageServiceFactory;
import com.mita.dto.StorageConfigDTO;
import com.mita.exception.InvalidStorageCredentialsException;
import com.mita.exception.TestsigmaException;
import com.mita.mapper.StorageConfigMapper;
import com.mita.model.StorageConfig;
import com.mita.service.StorageConfigService;
import com.mita.web.request.StorageConfigRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage_config")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class StorageConfigController {
  private final StorageConfigService storageConfigService;
  private final StorageConfigMapper storageConfigMapper;
  private final StorageServiceFactory storageServiceFactory;

  @RequestMapping(method = RequestMethod.GET)
  public StorageConfigDTO get() {
    return storageConfigMapper.map(storageConfigService.getStorageConfig());
  }

  @RequestMapping(method = RequestMethod.PUT)
  public StorageConfigDTO update(@RequestBody StorageConfigRequest storageConfigRequest) throws TestsigmaException {
    StorageConfig storageConfig = storageConfigMapper.map(storageConfigRequest);
    try {
      storageServiceFactory.validateCredentials(storageConfig);
    } catch (Exception e) {
      throw new InvalidStorageCredentialsException(e.getMessage());
    }
    StorageConfig storageConfigDto = storageConfigService.update(storageConfig);
    return storageConfigMapper.map(storageConfigDto);
  }

}
