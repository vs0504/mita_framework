package com.mita.agent.schedulers;

import com.mita.agent.constants.MobileOs;
import com.mita.agent.mobile.MobileDevice;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Log4j2
public class IOSEmulatorScheduler extends BaseScheduler {

    @Autowired
    public IOSEmulatorScheduler(WebApplicationContext webApplicationContext) {
        super(webApplicationContext);
    }

    // @Scheduled(cron = "0/20 * * * * *")
    public void run() {
        try {
            Thread.currentThread().setName("IOSEmulatorScheduler");
            if (skipScheduleRun()) {
                log.info("Skipping IOSEmulatorScheduler run...");
                return;
            }
            log.debug("Syncing ios emulator details");
            List<MobileDevice> initialSimulatorList = this.iosDeviceService.simulatorDeviceList();
            Map<String, MobileDevice> deviceMap = this.deviceContainer.getDeviceMap();
            Set<String> simulatorUniqueIds = new HashSet<>();
            for(MobileDevice simulator : initialSimulatorList) {
                simulatorUniqueIds.add(simulator.getUniqueId());
                if(!deviceMap.containsKey(simulator.getUniqueId())) {
                    log.info("Adding mobile device {} to device container", simulator.getUniqueId());
                    this.deviceContainer.addDevice(simulator);
                }
            }

            for(MobileDevice device : deviceMap.values()) {
                if(!simulatorUniqueIds.contains(device.getUniqueId()) && device.getOsName().equals(MobileOs.IOS)) {
                    log.info("Removing mobile device {} from device container", device.getUniqueId());
                    this.deviceContainer.deleteDevice(device.getUniqueId());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}

