package com.mita.service;

import com.mita.exception.MitaDatabaseException;
import com.mita.exception.MitaException;
import com.mita.repository.MobileInspectionRepository;
import com.mita.dto.MobileInspectionDTO;
import com.mita.mapper.MobileInspectionMapper;
import com.mita.model.WorkspaceType;
import com.mita.model.MobileInspection;
import com.mita.model.MobileInspectionStatus;
import com.mita.model.Platform;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MobileInspectionService {
  private final MobileInspectionRepository mobileInspectionRepository;
  private final MobileInspectionMapper mobileInspectionMapper;
  private final PlatformsService platformsService;
  private final TestDeviceResultService testDeviceResultService;

  public MobileInspection find(Long id) throws MitaDatabaseException {
    return mobileInspectionRepository.findById(id).orElseThrow(() -> new MitaDatabaseException("Mobile Inspection not found with" + id));
  }

  public MobileInspection create(MobileInspection mobileInspection) {
    return this.mobileInspectionRepository.save(mobileInspection);
  }

  public MobileInspection update(MobileInspection mobileInspection) {
    return this.mobileInspectionRepository.save(mobileInspection);
  }

  public Page<MobileInspection> findAll(Specification<MobileInspection> spec, Pageable pageable) {
    return this.mobileInspectionRepository.findAll(spec, pageable);
  }

  public List<MobileInspection> findAllByLastActiveAtBeforeAndStatusIn(Timestamp lastActiveAt, Collection<MobileInspectionStatus> statusTypes) {
    return this.mobileInspectionRepository.findAllByLastActiveAtBeforeAndStatusIn(lastActiveAt, statusTypes);
  }

  public MobileInspectionDTO closeSession(Long id) throws MitaException {
    log.info("Closing Mobile inspector session with id - " + id);
    MobileInspection mobileInspection = find(id);
    mobileInspection.setLastActiveAt(new Timestamp(System.currentTimeMillis()));
    mobileInspection.setFinishedAt(new Timestamp(System.currentTimeMillis()));
    mobileInspection.setStatus(MobileInspectionStatus.FINISHED);
    mobileInspection = update(mobileInspection);
    WorkspaceType workspaceType = WorkspaceType.AndroidNative;
    if (mobileInspection.getPlatform().equals(Platform.iOS))
      workspaceType = WorkspaceType.IOSNative;
    if (mobileInspection.getSessionId() != null) {
      platformsService.closePlatformSession(mobileInspection.getLabType());
    }
    try {
      testDeviceResultService.sendPendingTestPlans();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return mobileInspectionMapper.mapDTO(mobileInspection);
  }
}
