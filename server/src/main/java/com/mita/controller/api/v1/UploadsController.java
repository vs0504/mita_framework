

package com.mita.controller.api.v1;

import com.mita.specification.UploadSpecificationsBuilder;
import com.mita.dto.api.APIUploadDTO;
import com.mita.exception.ResourceNotFoundException;
import com.mita.exception.MitaException;
import com.mita.mapper.UploadMapper;
import com.mita.model.Upload;
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

@RestController(value = "apiUploadController")
@RequestMapping(path = "/api/v1/uploads")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class UploadsController {
  private final UploadService uploadService;
  private final UploadMapper uploadMapper;

  @RequestMapping(method = RequestMethod.POST)
  public APIUploadDTO create(@ModelAttribute @Valid UploadRequest uploadRequest)
    throws MitaException {
    if(uploadRequest.getVersion() == null)
      uploadRequest.setVersion(uploadRequest.getName());
    Upload upload = uploadService.create(uploadRequest);
    return uploadMapper.mapApi(upload);
  }

  @RequestMapping(method = RequestMethod.GET)
  public Page<APIUploadDTO> index(UploadSpecificationsBuilder builder, Pageable pageable) {
    Specification<Upload> spec = builder.build();
    Page<Upload> uploads = uploadService.findAll(spec, pageable);
    List<APIUploadDTO> uploadDTOS = uploadMapper.mapApis(uploads.getContent());
    return new PageImpl<>(uploadDTOS, pageable, uploads.getTotalElements());
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public APIUploadDTO show(@PathVariable("id") Long id) throws ResourceNotFoundException {
    return uploadMapper.mapApi(uploadService.find(id));
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
  public APIUploadDTO update(@PathVariable("id") Long id, @ModelAttribute UploadRequest uploadRequest)
    throws MitaException {
    Upload upload = uploadService.find(id);
    if(uploadRequest.getVersion() == null)
      uploadRequest.setVersion(upload.getLatestVersion().getName()+".1");
    upload = uploadService.update(upload, uploadRequest);
    return uploadMapper.mapApi(upload);
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
    uploadService.delete(uploadService.find(id));
  }
}
