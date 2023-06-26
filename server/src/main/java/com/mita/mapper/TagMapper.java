
package com.mita.mapper;

import com.mita.dto.TagDTO;
import com.mita.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
  TagDTO map(Tag tagModel);

  List<TagDTO> map(List<Tag> list);

}
