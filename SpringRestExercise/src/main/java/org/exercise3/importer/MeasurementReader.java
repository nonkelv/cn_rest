package org.exercise3.importer;

import org.exercise3.model.CarRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeasurementReader {

    @Autowired
    private MeasurementLineParser measurementLineParser;

    public List<CarRegistration> readMeasurementsFromFile(String fileName) throws IOException {
        List<CarRegistration> carRegistrations = new ArrayList<>();

        File file = ResourceUtils.getFile("classpath:" + fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                CarRegistration carRegistration = measurementLineParser.parseMeasurementLine(line);
                carRegistrations.add(carRegistration);
                line = reader.readLine();
            }
        }
        return carRegistrations;
    }
}
