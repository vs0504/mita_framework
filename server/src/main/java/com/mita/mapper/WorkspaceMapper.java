

package com.mita.mapper;

import com.mita.dto.WorkspaceDTO;
import com.mita.dto.export.ApplicationXMLDTO;
import com.mita.model.Workspace;
import com.mita.web.request.WorkspaceRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface WorkspaceMapper {
  List<ApplicationXMLDTO> mapApplications(List<Workspace> workspaces);

  WorkspaceDTO map(Workspace workspace);

  List<WorkspaceDTO> map(List<Workspace> workspaces);

  Workspace map(WorkspaceRequest request);

  void merge(@MappingTarget Workspace workspace, WorkspaceRequest request);
}
