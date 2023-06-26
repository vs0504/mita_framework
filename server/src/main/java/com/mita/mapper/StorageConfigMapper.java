package com.mita.mapper;

import com.mita.dto.StorageConfigDTO;
import com.mita.model.StorageConfig;
import com.mita.web.request.StorageConfigRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface StorageConfigMapper {

  StorageConfigDTO map(StorageConfig storageConfig);

  @Mapping(target = "azureBlobPreSignedURLTimeout", ignore = true)
  @Mapping(target = "awsS3PreSignedURLTimeout", ignore = true)
  StorageConfig map(StorageConfigRequest storageConfigRequest);
}
