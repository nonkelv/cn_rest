package org.exercise3.model;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

// class to pass time to the server for some requests

@Component
public class QueryTime {
    private LocalTime time;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
