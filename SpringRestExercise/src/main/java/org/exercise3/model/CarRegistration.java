package org.exercise3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.exercise3.constants.Constants;

import java.time.Duration;
import java.time.LocalTime;

@Entity(name="CAR_REGISTRATION")
public class CarRegistration {
    @Id
    @GeneratedValue
    private long id;

    private String license;

    private LocalTime entranceTime;
    private LocalTime exitTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public LocalTime getEntranceTime() {
        return entranceTime;
    }

    public void setEntranceTime(LocalTime entranceTime) {
        this.entranceTime = entranceTime;
    }

    public LocalTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalTime exitTime) {
        this.exitTime = exitTime;
    }

    public double getAverageSpeed() {
        Duration duration = Duration.between(entranceTime, exitTime);
        return Constants.LENGTH_KM * 3600000 / duration.toMillis();
    }

}
