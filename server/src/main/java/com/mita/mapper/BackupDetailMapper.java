

package com.mita.mapper;

import com.mita.dto.BackupDTO;
import com.mita.dto.BackupDetailDTO;
import com.mita.model.BackupDetail;
import com.mita.web.request.BackupRequest;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BackupDetailMapper {
  BackupDTO map(BackupRequest file);

  BackupDetail map(BackupDTO file);

  BackupDTO mapTo(BackupDetail backupDetail);

  BackupDetailDTO map(BackupDetail backupDetail);

  List<BackupDetailDTO> map(List<BackupDetail> backupDetails);
}
