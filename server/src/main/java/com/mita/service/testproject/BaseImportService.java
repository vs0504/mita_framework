package com.mita.service.testproject;

import com.mita.model.EntityExternalMapping;
import com.mita.model.EntityType;
import com.mita.model.Integrations;
import com.mita.service.EntityExternalMappingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public abstract class BaseImportService<T> {

    @Autowired
    private EntityExternalMappingService entityExternalMappingService;

    public void createEntityExternalMappingIfNotExists(String externalId,
                                                       EntityType entityType,
                                                       Long entityId,
                                                       Integrations integration){
        List<EntityExternalMapping> optionalEntityExternalMapping =
                entityExternalMappingService.findByExternalIdAndEntityTypeAndApplicationId(externalId, entityType,
                        integration.getId());
        if(!optionalEntityExternalMapping.isEmpty()) {
            return;
        }
        EntityExternalMapping entityExternalMapping = new EntityExternalMapping();
        entityExternalMapping.setApplicationId(integration.getId());
        entityExternalMapping.setExternalId(externalId);
        entityExternalMapping.setEntityType(entityType);
        entityExternalMapping.setEntityId(entityId);
        entityExternalMappingService.save(entityExternalMapping);
    }

}
