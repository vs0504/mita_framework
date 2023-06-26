package com.mita.os.stats.listener;

import com.mita.event.ElementEvent;
import com.mita.event.EventType;
import com.mita.exception.TestsigmaException;
import com.mita.model.Element;
import com.mita.os.stats.service.TestsigmaOsStatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ElementEventListener {
  private final TestsigmaOsStatsService testsigmaOsStatsService;

  @EventListener(classes = ElementEvent.class)
  public void OnElementEvent(ElementEvent<Element> event) {
    log.info("Caught ElementEvent - " + event);
    try {
      if (event.getEventType() == EventType.CREATE) {
        testsigmaOsStatsService.sendElementStats(event.getEventData(), com.mita.os.stats.event.EventType.CREATE);
      } else if (event.getEventType() == EventType.DELETE) {
        testsigmaOsStatsService.sendElementStats(event.getEventData(), com.mita.os.stats.event.EventType.DELETE);
      }
    } catch (TestsigmaException e) {
      log.error(e.getMessage(), e);
    }
  }
}
