package com.mita.util;

import com.mita.exception.MitaException;
import com.mita.factory.SchedulerFactory;
import com.mita.model.ScheduleType;
import com.mita.schedules.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchedulerService {
  private final SchedulerFactory schedulerFactory;

  public Timestamp getScheduleTime(ScheduleType scheduleType, Timestamp scheduleTime) throws ParseException {
    Schedule schedule = schedulerFactory.getSchedule(scheduleType);
    return schedule.getNextSchedule(scheduleTime);
  }

  public void validateScheduleTime(Timestamp scheduleTime) throws MitaException {
    Date nextScheduleDate = new Date(scheduleTime.getTime());
    if (new Timestamp(System.currentTimeMillis()).after(new Timestamp(this.dateFromUTC(nextScheduleDate).getTime()))) {
      throw new MitaException("Current time is greater than given time");
    }
  }

  public Date dateFromUTC(Date date) {
    return new Date(date.getTime() + Calendar.getInstance().getTimeZone().getOffset(new Date().getTime()));
  }

}
