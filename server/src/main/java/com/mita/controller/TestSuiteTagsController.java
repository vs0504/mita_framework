package com.mita.controller;

import com.mita.mapper.TagMapper;
import com.mita.model.TagType;
import com.mita.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testsuite_tags")
@CrossOrigin
public class TestSuiteTagsController extends TagsController {

  @Autowired
  public TestSuiteTagsController(TagMapper mapper, TagService tagService) {
    super(mapper, tagService);
  }

  @Override
  protected TagType getTagType() {
    return TagType.TEST_SUITE;
  }
}