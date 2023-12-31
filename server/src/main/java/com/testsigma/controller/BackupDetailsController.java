/*
 * *****************************************************************************
 *  Copyright (C) 2020 Testsigma Technologies Inc.
 *  All rights reserved.
 *  ****************************************************************************
 */

package com.testsigma.controller;

import com.testsigma.constants.MessageConstants;
import com.testsigma.dto.BackupDetailDTO;
import com.testsigma.exception.ResourceNotFoundException;
import com.testsigma.exception.TestProjectImportException;
import com.testsigma.exception.TestsigmaException;
import com.testsigma.mapper.BackupDetailMapper;
import com.testsigma.model.BackupDetail;
import com.testsigma.model.BackupStatus;
import com.testsigma.service.BackupDetailService;
import com.testsigma.service.testproject.ProjectImportService;
import com.testsigma.service.testproject.TestProjectImportService;
import com.testsigma.web.request.BackupRequest;
import com.testsigma.web.request.ExternalImportRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/settings/backups")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@CrossOrigin
public class BackupDetailsController {

  private final BackupDetailMapper mapper;
  private final BackupDetailService service;
  private final TestProjectImportService testProjectImportService;
  private final ProjectImportService projectImportService;

  @GetMapping(path = "/{id}/download")
  @ResponseStatus(HttpStatus.PERMANENT_REDIRECT)
  public void download(@PathVariable("id") Long id, HttpServletResponse response) throws ResourceNotFoundException, IOException {
    BackupDetail detail = this.service.find(id);
    Optional<URL> s3Url = this.service.downLoadURL(detail);
    if (!s3Url.isPresent()) {
      throw new ResourceNotFoundException("Backup file is missing in storage");
    }
    response.sendRedirect(s3Url.get().toString());
  }

  @RequestMapping(method = RequestMethod.POST, value = "/test_project", consumes = {"multipart/form-data"})
  public void createTestProjectImport(@RequestPart ExternalImportRequest request,
                                      @RequestPart(name = "file", required = false) MultipartFile file) throws IOException, TestsigmaException {
    if(request.isYamlImport())
      testProjectImportService.yamlImport(file);
    projectImportService.createBackupDetailEntry(file);
  }

  @GetMapping(path = "/{id}/download_testcases")
  @ResponseStatus(HttpStatus.PERMANENT_REDIRECT)
  public void downloadTestCasesList(@PathVariable("id") Long id, HttpServletResponse response) throws ResourceNotFoundException, IOException {
    BackupDetail detail = this.service.find(id);
    Optional<URL> s3Url = this.service.getTestCasesPreSignedURL(detail);
    if (!s3Url.isPresent()) {
      throw new ResourceNotFoundException("XLS file is missing in storage");
    }
    response.sendRedirect(s3Url.get().toString());
  }

  @PostMapping(path ="/export")
  public void backup(@RequestBody BackupRequest request) throws IOException, TestsigmaException {
    service.export(request);
  }

  @PostMapping(consumes = {"multipart/form-data"})
  public void create(@RequestPart BackupRequest request, @RequestPart(name = "file", required = false) MultipartFile file) throws IOException {
    service.create(request, file);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void destroy(@PathVariable("id") Long id) throws ResourceNotFoundException {
    this.service.destroy(id);
  }

  @GetMapping
  public Page<BackupDetailDTO> index(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
    Page<BackupDetail> backupDetails = service.findAll(pageable);
    List<BackupDetailDTO> dtos = mapper.map(backupDetails.getContent());
    return new PageImpl<>(dtos, pageable, backupDetails.getTotalElements());
  }
}
