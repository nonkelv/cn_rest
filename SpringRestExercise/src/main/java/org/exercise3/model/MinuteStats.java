package org.exercise3.model;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class MinuteStats {
    private LocalTime time;
    private int passedVehicles;
    private double trafficIntensity;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getPassedVehicles() {
        return passedVehicles;
    }

    public void setPassedVehicles(int passedVehicles) {
        this.passedVehicles = passedVehicles;
    }

    public double getTrafficIntensity() {
        return trafficIntensity;
    }

    public void setTrafficIntensity(double trafficIntensity) {
        this.trafficIntensity = trafficIntensity;
    }
}
