package org.exercise3.model;

import org.springframework.stereotype.Component;

@Component
public class CarStats {
    private String license;
    private double averageSpeed;
    private int overtaken;

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public int getOvertaken() {
        return overtaken;
    }

    public void setOvertaken(int overtaken) {
        this.overtaken = overtaken;
    }
}
