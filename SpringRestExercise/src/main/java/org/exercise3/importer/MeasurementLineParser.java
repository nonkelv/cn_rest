package org.exercise3.importer;

import org.exercise3.constants.Constants;
import org.exercise3.model.CarRegistration;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class MeasurementLineParser {

    public CarRegistration parseMeasurementLine(String line) {
        String[] splitLine = line.split(Constants.MEASUREMENT_FIELD_SEPARATOR);

        CarRegistration carRegistration = new CarRegistration();
        carRegistration.setLicense(splitLine[0]);
        carRegistration.setEntranceTime(composeTime(splitLine[1], splitLine[2], splitLine[3], splitLine[4]));
        carRegistration.setExitTime(composeTime(splitLine[5], splitLine[6], splitLine[7], splitLine[8]));

        return carRegistration;
    }

    private LocalTime composeTime(String hour, String minute, String second, String milliSec) {
        return LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute), Integer.parseInt(second), Integer.parseInt(milliSec) * 1000000);
    }
}
