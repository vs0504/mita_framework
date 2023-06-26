

package com.mita.controller;

import com.mita.specification.TestDeviceSpecificationsBuilder;
import com.mita.dto.TestDeviceDTO;
import com.mita.mapper.TestDeviceMapper;
import com.mita.model.TestDevice;
import com.mita.service.TestDeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping(path = "/test_devices", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class TestDevicesController {
  private final TestDeviceService testDeviceService;
  private final TestDeviceMapper testDeviceMapper;

  @RequestMapping(method = RequestMethod.GET)
  public Page<TestDeviceDTO> index(TestDeviceSpecificationsBuilder builder, Pageable pageable) {
    Specification<TestDevice> spec = builder.build();
    Page<TestDevice> executionEnvironments = testDeviceService.findAll(spec, pageable);
    List<TestDeviceDTO> testDeviceDTOS =
      testDeviceMapper.map(executionEnvironments.getContent());
    return new PageImpl<>(testDeviceDTOS, pageable, executionEnvironments.getTotalElements());
  }
}
