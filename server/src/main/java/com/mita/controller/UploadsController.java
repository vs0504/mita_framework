

package com.mita.controller;


import com.mita.specification.UploadSpecificationsBuilder;
import com.mita.dto.UploadDTO;
import com.mita.exception.ResourceNotFoundException;
import com.mita.exception.MitaException;
import com.mita.mapper.UploadMapper;
import com.mita.model.Upload;
import com.mita.service.TestDeviceService;
import com.mita.service.UploadService;
import com.mita.web.request.UploadRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/uploads")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@CrossOrigin
public class UploadsController {

  private final UploadService uploadService;
  private final TestDeviceService testDeviceService;
  private final UploadMapper uploadMapper;

  @GetMapping
  public Page<UploadDTO> index(UploadSpecificationsBuilder builder, Pageable pageable) {
    Specification<Upload> spec = builder.build();
    Page<Upload> uploads = uploadService.findAll(spec, pageable);
    List<UploadDTO> uploadDTOS = uploadMapper.map(uploads.getContent());
    return new PageImpl<>(uploadDTOS, pageable, uploads.getTotalElements());
  }

  @PostMapping
  public UploadDTO create(@ModelAttribute @Valid UploadRequest uploadRequest)
    throws MitaException {
    Upload upload = uploadService.create(uploadRequest);
    return uploadMapper.map(upload);
  }

  @PostMapping(path = "/{id}")
  public UploadDTO update(@PathVariable("id") Long id, @ModelAttribute UploadRequest uploadRequest)
    throws MitaException {
    Upload upload = uploadService.find(id);
    upload = uploadService.update(upload, uploadRequest);
    return uploadMapper.map(upload);
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public UploadDTO show(@PathVariable("id") Long id) throws ResourceNotFoundException {
    return uploadMapper.map(uploadService.find(id));
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
    this.testDeviceService.resentAppUploadIdToNull(id);
    uploadService.delete(uploadService.find(id));
  }

  @DeleteMapping(value = "/bulk")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void bulkDelete(@RequestParam(value = "ids[]") Long[] ids,@RequestParam(value = "workspaceVersionId") Long workspaceVersionId) throws Exception {
      uploadService.bulkDelete(ids, workspaceVersionId);
  }
}
