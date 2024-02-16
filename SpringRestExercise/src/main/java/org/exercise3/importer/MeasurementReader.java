package org.exercise3.importer;

import org.aspectj.util.FileUtil;
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

        File file = ResourceUtils.getFile(fileName);
        // I don't seem to get the txt file in the jar, and I'm not sure I even want that,
        // so to get both working, let's just add the 2 possibilities:
        // first try to find it next to the jar, and if not found in the resource folder.
        if (!file.exists())
            file = ResourceUtils.getFile("classpath:" + fileName);
        // should split this in a separate method I guess, but it's Friday afternoon and I am getting tired...

        if (file.exists())
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
