

package com.mita.mapper;

import com.mita.dto.WorkspaceVersionDTO;
import com.mita.dto.export.ApplicationVersionXMLDTO;
import com.mita.model.WorkspaceVersion;
import com.mita.web.request.WorkspaceVersionRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface WorkspaceVersionMapper {
  List<ApplicationVersionXMLDTO> mapVersions(List<WorkspaceVersion> workspaceVersions);

  WorkspaceVersion copy(WorkspaceVersion workspaceVersion);

  WorkspaceVersionDTO map(WorkspaceVersion workspaceVersion);

  List<WorkspaceVersionDTO> map(List<WorkspaceVersion> workspaceVersion);

  WorkspaceVersion map(WorkspaceVersionRequest request);

  void merge(@MappingTarget WorkspaceVersion workspaceVersion, WorkspaceVersionRequest request);
}
