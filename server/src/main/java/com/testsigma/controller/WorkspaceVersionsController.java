/*
 * *****************************************************************************
 *  Copyright (C) 2020 Testsigma Technologies Inc.
 *  All rights reserved.
 *  ****************************************************************************
 */

package com.testsigma.controller;

import com.testsigma.dto.WorkspaceVersionDTO;
import com.testsigma.exception.ResourceNotFoundException;
import com.testsigma.exception.TestsigmaDatabaseException;
import com.testsigma.mapper.WorkspaceVersionMapper;
import com.testsigma.model.WorkspaceVersion;
import com.testsigma.model.WorkspaceVersionMapping;
import com.testsigma.repository.UserOnboardingRepository;
import com.testsigma.service.CurrentUserService;
import com.testsigma.service.WorkspaceService;
import com.testsigma.service.WorkspaceVersionMappingService;
import com.testsigma.service.WorkspaceVersionService;
import com.testsigma.specification.VersionSpecificationsBuilder;
import com.testsigma.web.request.WorkspaceVersionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/workspace_versions")
@Log4j2
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WorkspaceVersionsController {

  private final WorkspaceVersionService workspaceVersionService;
  private final WorkspaceVersionMapper workspaceVersionMapper;
  private final WorkspaceService workspaceService;
  private final UserOnboardingRepository userOnboardingRepository;
  private final WorkspaceVersionMappingService workspaceVersionMappingService;


  @GetMapping(path = "/{id}")
  public WorkspaceVersionDTO show(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
    log.info("Request /workspace_versions/" + id);
    WorkspaceVersion applicationVersion = workspaceVersionService.find(id);
    return workspaceVersionMapper.map(applicationVersion);
  }

  @GetMapping
  public Page<?> index(VersionSpecificationsBuilder builder,
                                         Pageable pageable) {
    log.info("Request /workspace_versions/");
    String email = CurrentUserService.getCurrentUser().getEmail();
    log.info(" current email"+email);
    Map<String, Object> userData = userOnboardingRepository.findByUserDetails(email);

    Specification<WorkspaceVersion> spec = builder.build();
    Page<WorkspaceVersion> versions = workspaceVersionService.findAll(spec, pageable);
    List<WorkspaceVersionDTO> dtos =
      workspaceVersionMapper.map(versions.getContent());


    List<WorkspaceVersionMapping> workspaceVersionMappingList= workspaceVersionMappingService.findWorkSpaceVersionByUserId1(userData.get("id"));
    List<WorkspaceVersionDTO> finaldtos = new ArrayList<>();
    for(WorkspaceVersionDTO  workSpaceVersion: dtos) {
      List<WorkspaceVersionMapping> workspaceVersionMappingresultList = workspaceVersionMappingList.stream().filter(project -> project.getWorkspaceVersionId().toString().equals(workSpaceVersion.getId().toString())).collect(Collectors.toList());
      if (workspaceVersionMappingresultList.size() > 0 && workspaceVersionMappingresultList.get(0).getStatus().equalsIgnoreCase("ACTIVE")) {
        finaldtos.add(workSpaceVersion);
      }
    }

    return new PageImpl<>(finaldtos,pageable,finaldtos.size());
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void destroy(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
    log.info("Delete /workspace_versions/" + id);
    this.workspaceVersionService.destroy(id);
  }

  @PutMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public WorkspaceVersionDTO update(@PathVariable(value = "id") Long id, @RequestBody WorkspaceVersionRequest request) throws ResourceNotFoundException {
    log.info("Put /workspace_versions/" + id + " data:" + request);
    WorkspaceVersion version = this.workspaceVersionService.find(id);
    version.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
    workspaceVersionMapper.merge(version, request);
    version = this.workspaceVersionService.update(version);
    version = this.workspaceVersionService.find(version.getId());
    return this.workspaceVersionMapper.map(version);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public WorkspaceVersionDTO create(@RequestBody WorkspaceVersionRequest request) throws ResourceNotFoundException {
    log.info("Post /workspace_versions/ data:" + request);
    WorkspaceVersion version = this.workspaceVersionMapper.map(request);
    version.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    version.setWorkspace(workspaceService.find(version.getWorkspaceId()));
    version = this.workspaceVersionService.create(version);
    version = this.workspaceVersionService.find(version.getId());
    return this.workspaceVersionMapper.map(version);
  }


  @PostMapping(path = "/clone")
  @ResponseStatus(HttpStatus.CREATED)
  public WorkspaceVersionDTO clone(@RequestBody WorkspaceVersionRequest request) throws ResourceNotFoundException, TestsigmaDatabaseException {
    WorkspaceVersion version = this.workspaceVersionMapper.map(request);
    version = this.workspaceVersionService.copy(version, request.getId());
    version = this.workspaceVersionService.find(version.getId());
    return this.workspaceVersionMapper.map(version);
  }

}
