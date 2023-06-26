

package com.mita.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mita.exception.ResourceNotFoundException;
import com.mita.repository.ElementScreenNameRepository;
import com.mita.specification.SearchCriteria;
import com.mita.specification.SearchOperation;
import com.mita.dto.BackupDTO;
import com.mita.dto.export.ElementScreenNameCloudXMLDTO;
import com.mita.dto.export.ElementScreenNameXMLDTO;
import com.mita.mapper.ElementScreenNameMapper;
import com.mita.model.ElementScreenName;
import com.mita.web.request.ElementScreenNameRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class ElementScreenService extends XMLExportImportService<ElementScreenName> {
  private final ElementScreenNameRepository elementScreenNameRepository;
  private final ElementScreenNameMapper elementScreenNameMapper;
  public final String DEFAULT_SCREEN_NAME ="DEFAULT_SCREEN_NAME";

  public Page<ElementScreenName> findAll(Specification<ElementScreenName> specification, Pageable page) {
    return elementScreenNameRepository.findAll(specification, page);
  }

  public Page<ElementScreenName> findAllByWorkspaceVersionId(Long workspaceVersionId, Pageable page) {
    return elementScreenNameRepository.findAllByWorkspaceVersionId(workspaceVersionId, page);
  }

  public List<ElementScreenName> findAllByWorkspaceVersionId(Long workspaceVersionId) {
    return elementScreenNameRepository.findAllByWorkspaceVersionId(workspaceVersionId);
  }

  public ElementScreenName save(ElementScreenNameRequest screenNameKey) {
    Optional<ElementScreenName> result =
      elementScreenNameRepository.findByNameAndWorkspaceVersionId(screenNameKey.getName(), screenNameKey.getWorkspaceVersionId());
    ElementScreenName screenName = null;
    if (!result.isPresent()) {
      screenName = elementScreenNameMapper.map(screenNameKey);
      screenName.setName(screenNameKey.getName());
      screenName.setWorkspaceVersionId(screenNameKey.getWorkspaceVersionId());
      screenName = elementScreenNameRepository.save(screenName);
    } else {
      screenName = result.get();
      screenName = elementScreenNameRepository.save(screenName);
    }
    return screenName;
  }

  public void export(BackupDTO backupDTO) throws IOException, ResourceNotFoundException {
    if (!backupDTO.getIsElementScreenNameEnabled()) return;
    log.debug("backup process for element screen name  initiated");
    writeXML("element_screen_names", backupDTO, PageRequest.of(0, 25));
    log.debug("backup process for element screen name  completed");
  }

  public Specification<ElementScreenName> getExportXmlSpecification(BackupDTO backupDTO) throws ResourceNotFoundException {
    SearchCriteria criteria = new SearchCriteria("workspaceVersionId", SearchOperation.EQUALITY, backupDTO.getWorkspaceVersionId());
    List<SearchCriteria> params = new ArrayList<>();
    params.add(criteria);
    ElementScreenNameSpecificationsBuilder elementScreenNameSpecificationsBuilder = new ElementScreenNameSpecificationsBuilder();
    elementScreenNameSpecificationsBuilder.params = params;
    return elementScreenNameSpecificationsBuilder.build();
  }

  @Override
  protected List<ElementScreenNameXMLDTO> mapToXMLDTOList(List<ElementScreenName> list) {
    return elementScreenNameMapper.mapElementScreenNameList(list);
  }

  public void importXML(BackupDTO importDTO) throws IOException, ResourceNotFoundException {
    if (!importDTO.getIsElementScreenNameEnabled()) return;
    log.debug("import process for elements screen name initiated");
    if (importDTO.getIsCloudImport())
      importFiles("ui_identifier_screen_names", importDTO);
    else
      importFiles("element_screen_names", importDTO);
    log.debug("import process for element screen name completed");
  }

  @Override
  public List<ElementScreenName> readEntityListFromXmlData(String xmlData, XmlMapper xmlMapper, BackupDTO importDTO) throws JsonProcessingException {
    if (importDTO.getIsCloudImport()) {
      return elementScreenNameMapper.mapCloudElementScreenNamesList(xmlMapper.readValue(xmlData, new TypeReference<List<ElementScreenNameCloudXMLDTO>>() {
      }));
    }
    else{
      return elementScreenNameMapper.mapElementScreenNamesList(xmlMapper.readValue(xmlData, new TypeReference<List<ElementScreenNameXMLDTO>>() {
      }));
    }
  }

  @Override
  public Optional<ElementScreenName> findImportedEntity(ElementScreenName uiIdentifier, BackupDTO importDTO) {
    Optional<ElementScreenName> previous = elementScreenNameRepository.findAllByWorkspaceVersionIdAndImportedId(importDTO.getWorkspaceVersionId(), uiIdentifier.getId());
    return previous;
  }

  @Override
  public ElementScreenName processBeforeSave(Optional<ElementScreenName> previous, ElementScreenName present, ElementScreenName toImport, BackupDTO importDTO) {
    present.setImportedId(present.getId());
    if (previous.isPresent() && importDTO.isHasToReset()) {
      present.setId(previous.get().getId());
    } else {
      present.setId(null);
    }

    present.setWorkspaceVersionId(importDTO.getWorkspaceVersionId());
    return present;
  }


  @Override
  public ElementScreenName copyTo(ElementScreenName uiIdentifier) {
    return elementScreenNameMapper.copy(uiIdentifier);
  }

  @Override
  public ElementScreenName save(ElementScreenName uiIdentifier) {
    try {
      return elementScreenNameRepository.save(uiIdentifier);
    }catch (DataIntegrityViolationException exception){
      log.error(exception,exception);
    }
    return uiIdentifier;
  }

  @Override
  public Optional<ElementScreenName> getRecentImportedEntity(BackupDTO importDTO, Long... ids) {
    Long importedFrom = ids[0];
    return elementScreenNameRepository.findAllByWorkspaceVersionIdAndImportedId(importDTO.getWorkspaceVersionId(), importedFrom);
  }

  public Optional<ElementScreenName> findImportedEntityHavingSameName(Optional<ElementScreenName> previous, ElementScreenName current, BackupDTO importDTO) {
    Optional<ElementScreenName> oldEntity = elementScreenNameRepository.findByNameAndWorkspaceVersionId(current.getName(), importDTO.getWorkspaceVersionId());
    return oldEntity;
  }

  public boolean hasImportedId(Optional<ElementScreenName> previous) {
    return previous.isPresent() && previous.get().getImportedId() != null;
  }

  public boolean isEntityAlreadyImported(Optional<ElementScreenName> previous, ElementScreenName current) {
    return previous.isPresent() && previous.get().getImportedId() != null && previous.get().getImportedId().equals(current.getId());
  }

  @Override
  public boolean hasToSkip(ElementScreenName uiIdentifierScreenName, BackupDTO importDTO) {
    return false;
  }

  @Override
  void updateImportedId(ElementScreenName uiIdentifierScreenName, ElementScreenName previous, BackupDTO importDTO) {
    previous.setImportedId(uiIdentifierScreenName.getId());
    save(previous);
  }
}