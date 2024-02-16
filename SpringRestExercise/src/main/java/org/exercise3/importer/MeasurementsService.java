package org.exercise3.importer;

import org.exercise3.CarRegistrationRepository;
import org.exercise3.model.CarRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MeasurementsService {
    @Autowired
    private MeasurementReader measurementReader;

    @Autowired
    private CarRegistrationRepository carRegistrationRepository;

    public void run() {
        try {
            String fileName = "measurements.txt";
            List<CarRegistration> carRegistrations = measurementReader.readMeasurementsFromFile(fileName);
            for (CarRegistration carRegistration : carRegistrations) {
                carRegistrationRepository.save(carRegistration);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
