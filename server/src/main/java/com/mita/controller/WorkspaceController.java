

package com.mita.controller;

import com.mita.specification.ApplicationSpecificationsBuilder;
import com.mita.dto.WorkspaceDTO;
import com.mita.exception.ResourceNotFoundException;
import com.mita.mapper.WorkspaceMapper;
import com.mita.model.Workspace;
import com.mita.service.WorkspaceService;
import com.mita.web.request.WorkspaceRequest;
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
import java.util.List;

@RestController
@RequestMapping(path = "/workspaces")
@Log4j2
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WorkspaceController {

  private final WorkspaceService workspaceService;
  private final WorkspaceMapper workspaceMapper;

  @GetMapping(path = "/{id}")
  public WorkspaceDTO show(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
    log.info("Request /applications/" + id);
    Workspace workspace = workspaceService.find(id);
    return workspaceMapper.map(workspace);
  }

  @PutMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public WorkspaceDTO update(@PathVariable(value = "id") Long id, @RequestBody WorkspaceRequest request) throws ResourceNotFoundException {
    log.info("Put Request /applications/" + id + " data" + request);
    Workspace workspace = workspaceService.find(id);
    workspace.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
    this.workspaceMapper.merge(workspace, request);
    workspace = this.workspaceService.update(workspace);
    return workspaceMapper.map(workspace);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public WorkspaceDTO create(@RequestBody WorkspaceRequest request) throws ResourceNotFoundException {
    log.info("Post Request /applications/ data" + request);
    Workspace workspace = workspaceMapper.map(request);
    workspace.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    workspace = this.workspaceService.create(workspace, true);
    return workspaceMapper.map(workspace);
  }

  @GetMapping
  public Page<WorkspaceDTO> index(ApplicationSpecificationsBuilder builder,
                                  Pageable pageable) {
    log.info("Request /applications/");
    Specification<Workspace> spec = builder.build();
    Page<Workspace> versions = workspaceService.findAll(spec, pageable);
    List<WorkspaceDTO> dtos =
      workspaceMapper.map(versions.getContent());
    return new PageImpl<>(dtos, pageable, versions.getTotalElements());
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void destroy(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
    log.info("Delete /applications/" + id);
    workspaceService.destroy(id);
  }
}
