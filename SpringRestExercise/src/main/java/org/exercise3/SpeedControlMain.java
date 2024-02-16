package org.exercise3;

import org.exercise3.importer.MeasurementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpeedControlMain {

    @Autowired
    MeasurementsService measurementsService; // why is this "not recommended" ?

    public static void main(String[] args) {
        SpringApplication.run(SpeedControlMain.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        measurementsService.run();
    }
}
