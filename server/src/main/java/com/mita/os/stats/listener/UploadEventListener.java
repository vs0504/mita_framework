package com.mita.os.stats.listener;

import com.mita.event.EventType;
import com.mita.event.UploadEvent;
import com.mita.exception.MitaException;
import com.mita.model.Upload;
import com.mita.os.stats.service.TestsigmaOsStatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UploadEventListener {
  private final TestsigmaOsStatsService testsigmaOsStatsService;

  @EventListener(classes = UploadEvent.class)
  public void OnUploadEvent(UploadEvent<Upload> event) {
    log.info("Caught UploadEvent - " + event);
    try {
      if (event.getEventType() == EventType.CREATE) {
        testsigmaOsStatsService.sendUploadStats(event.getEventData(), com.mita.os.stats.event.EventType.CREATE);
      } else if (event.getEventType() == EventType.DELETE) {
        testsigmaOsStatsService.sendUploadStats(event.getEventData(), com.mita.os.stats.event.EventType.DELETE);
      }
    } catch (MitaException e) {
      log.error(e.getMessage(), e);
    }
  }
}
