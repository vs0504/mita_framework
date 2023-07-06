package com.mita.controller;

import com.mita.dto.TagDTO;
import com.mita.exception.MitaDatabaseException;
import com.mita.exception.MitaException;
import com.mita.mapper.TagMapper;
import com.mita.model.TagType;
import com.mita.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public abstract class TagsController {

  private final TagMapper mapper;
  private final TagService tagService;

  @RequestMapping(method = RequestMethod.GET)
  public List<TagDTO> index() throws MitaException {
    return mapper.map(tagService.list(getTagType()));
  }

  @RequestMapping(path = "/associate_item/{id}", method = RequestMethod.POST)
  public HttpStatus save(@PathVariable("id") Long id, @RequestBody List<String> tags)
    throws MitaException {
    tagService.updateTags(tags, getTagType(), id);
    return HttpStatus.OK;
  }

  @RequestMapping(path = "/associate_item/{id}", method = RequestMethod.GET)
  public List<TagDTO> index(@PathVariable("id") Long id) throws MitaException {
    return mapper.map(tagService.assignedLst(getTagType(), id));
  }

  @ExceptionHandler({MitaDatabaseException.class, MitaException.class})
  public ResponseEntity<Object> handleTagNotFoundException(Exception ex) {

    return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.OK);
  }

  protected abstract TagType getTagType();
}
