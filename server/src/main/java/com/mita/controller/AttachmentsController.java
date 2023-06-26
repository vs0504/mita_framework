

package com.mita.controller;

import com.mita.specification.AttachmentSpecificationsBuilder;
import com.mita.dto.AttachmentDTO;
import com.mita.exception.ResourceNotFoundException;
import com.mita.mapper.AttachmentMapper;
import com.mita.model.Attachment;
import com.mita.service.AttachmentService;
import com.mita.web.request.AttachmentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(path = "/attachments")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class AttachmentsController {
  private final AttachmentService attachmentService;
  private final AttachmentMapper attachmentMapper;

  @RequestMapping(method = RequestMethod.POST)
  public AttachmentDTO create(@ModelAttribute @Valid AttachmentRequest attachmentRequest) throws IOException {
    Attachment attachment = this.attachmentService.create(attachmentRequest);
    return attachmentMapper.mapToDTO(attachment);
  }

  @RequestMapping(method = RequestMethod.GET)
  public Page<AttachmentDTO> index(AttachmentSpecificationsBuilder builder, Pageable pageable) {
    Specification<Attachment> spec = builder.build();
    Page<Attachment> attachments = this.attachmentService.findAll(spec, pageable);
    List<AttachmentDTO> attachmentDTOS =
      attachmentMapper.mapToDTO(attachments.getContent());
    return new PageImpl<>(attachmentDTOS, pageable, attachments.getTotalElements());
  }

  @GetMapping(path = "/{id}")
  public AttachmentDTO show(@PathVariable("id") Long id) throws ResourceNotFoundException {
    Attachment attachment = this.attachmentService.find(id);
    return attachmentMapper.mapToDTO(attachment);
  }

  @GetMapping(path = "/{id}/preview")
  @ResponseStatus(code = HttpStatus.MOVED_TEMPORARILY)
  public void preview(@PathVariable("id") Long id, HttpServletResponse httpServletResponse) throws ResourceNotFoundException {
    Attachment attachment = this.attachmentService.find(id);
    httpServletResponse.setHeader("Location", attachment.getPreSignedURL());
    httpServletResponse.setStatus(HttpStatus.MOVED_TEMPORARILY.value());
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
    this.attachmentService.destroy(id);
  }

}
