/*
 * *****************************************************************************
 *  Copyright (C) 2020 Testsigma Technologies Inc.
 *  All rights reserved.
 *  ****************************************************************************
 */

package com.testsigma.controller;

import com.testsigma.dto.TestDataProfileDTO;
import com.testsigma.exception.ResourceNotFoundException;
import com.testsigma.exception.TestsigmaValidationException;
import com.testsigma.mapper.TestDataProfileMapper;
import com.testsigma.model.TestData;
import com.testsigma.service.TestDataProfileService;
import com.testsigma.service.TestDataProfilesXLSImportService;
import com.testsigma.specification.TestDataProfileSpecificationsBuilder;
import com.testsigma.util.ReadExcel;
import com.testsigma.web.request.TestDataProfileRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/test_data", consumes = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class TestDataProfilesController {
  private final TestDataProfileService service;
  private final TestDataProfilesXLSImportService testDataXLSImportService;
  private final TestDataProfileMapper mapper;

  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public TestDataProfileDTO show(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
    TestData testData = service.find(id);
    return mapper.mapToDTO(testData);
  }

  @RequestMapping(method = RequestMethod.GET)
  public Page<TestDataProfileDTO> index(TestDataProfileSpecificationsBuilder builder, Pageable pageable) {
    Specification<TestData> spec = builder.build();
    Page<TestData> testData = this.service.findAll(spec, pageable);
    List<TestDataProfileDTO> testDataProfileDTOS =
      mapper.mapToDTO(testData.getContent());
    return new PageImpl<>(testDataProfileDTOS, pageable, testData.getTotalElements());
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void destroy(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
    service.destroy(id);
  }

  @PutMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public TestDataProfileDTO update(@PathVariable(value = "id") Long id, @RequestBody TestDataProfileRequest testDataProfileRequest) throws ResourceNotFoundException {
    TestData testData = this.service.find(id);
    mapper.merge(testDataProfileRequest, testData);
    testData.setUpdatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    testData = this.service.update(testData);
    return this.mapper.mapToDTO(testData);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TestDataProfileDTO create(@RequestBody TestDataProfileRequest testDataProfileRequest) throws ResourceNotFoundException {
    TestData testData = mapper.map(testDataProfileRequest);
    testData.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    testData = service.create(testData);
    return mapper.mapToDTO(testData);
  }

  @PutMapping(path = "/import_field_names", consumes = "multipart/form-data")
  @PreAuthorize("hasPermission('TEST_DATA','WRITE')")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public List<Object> importFromXls(
          @RequestParam(value = "file") MultipartFile file) throws TestsigmaValidationException, IOException {
    Workbook workBook = ReadExcel.getExcelWorkBook(file);
    return service.importFieldNames(workBook);
  }

  @PutMapping(path = "/import", consumes = "multipart/form-data")
  @PreAuthorize("hasPermission('TEST_DATA','WRITE')")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void importFromXls(
          @RequestParam(value = "file") MultipartFile testDataFile,
          @RequestParam(value = "name") String name,
          @RequestParam(value = "passwords[]", required = false) List<String> passwords,
          @RequestParam(value = "isReplace") Boolean isReplace,
          @RequestParam(value = "versionId") Long versionId) throws Exception {
    testDataXLSImportService.importFile(name, passwords, testDataFile, versionId, isReplace);
    log.debug("test data import completed");
  }

  @DeleteMapping(value = "/bulk")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void bulkDelete(@RequestParam(value = "ids[]") Long[] ids) throws Exception {
    service.bulkDestroy(ids);
  }
}
