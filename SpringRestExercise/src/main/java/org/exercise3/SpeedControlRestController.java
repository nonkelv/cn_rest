package org.exercise3;

import org.exercise3.constants.Constants;
import org.exercise3.exceptions.ResourceNotFoundException;
import org.exercise3.model.CarStats;
import org.exercise3.model.CarRegistration;
import org.exercise3.model.MinuteStats;
import org.exercise3.model.QueryTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("registrations")
class SpeedControlRestController {

    @Autowired
    private CarRegistrationRepository service;
    // is it correct to directly access the repository, or should we use the service in between?

    @GetMapping
    public List<CarRegistration> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public CarRegistration findById(@PathVariable("id") Long id) {
        return service.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody CarRegistration resource) {
        RestPreconditions.checkNotNull(resource);
        return service.save(resource).getId();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody CarRegistration resource) {
        RestPreconditions.checkNotNull(resource);
        service.findById(id).orElseThrow(ResourceNotFoundException::new);
        service.save(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    // extra queries

    // The original exercise only looked at the hour, well this has full time possibility
    @PostMapping(value = "/exitbeforetime")
    public List<CarRegistration> findExitedBefore(@RequestBody QueryTime resource) {
        RestPreconditions.checkNotNull(resource);
        RestPreconditions.checkNotNull(resource.getTime()); // is this needed?
        List<CarRegistration> registrations = new ArrayList<>();
        for (CarRegistration carRegistration : service.findAll()) {
            if (carRegistration.getExitTime().isBefore(resource.getTime()))
                registrations.add(carRegistration);
        }
        return registrations;
    }

    @PostMapping(value = "/minutestats")
    public MinuteStats getMinuteStatistics(@RequestBody QueryTime resource) {
        RestPreconditions.checkNotNull(resource);
        RestPreconditions.checkNotNull(resource.getTime()); // is this needed?

        LocalTime startTime = resource.getTime().truncatedTo(ChronoUnit.MINUTES);
        LocalTime endtime = startTime.plusMinutes(1);
        int entryCounter = 0;
        int intensityCounter = 0;
        for (CarRegistration carRegistration : service.findAll()) {
            if (!carRegistration.getEntranceTime().isBefore(startTime) && carRegistration.getEntranceTime().isBefore(endtime))
                ++entryCounter;
            if (carRegistration.getEntranceTime().isBefore(endtime) && carRegistration.getExitTime().isAfter(startTime))
                ++intensityCounter;
        }
        MinuteStats stats = new MinuteStats();
        stats.setTime(startTime);
        stats.setPassedVehicles(entryCounter);
        stats.setTrafficIntensity(intensityCounter / Constants.LENGTH_KM);
        return stats;
    }

    @GetMapping(value = "/fastest")
    public CarStats findFastest() {
        CarRegistration fastestCar = RestPreconditions.checkNotNull(findFastestRegistration());

        CarStats carStats = new CarStats();
        carStats.setLicense(fastestCar.getLicense());
        carStats.setAverageSpeed(fastestCar.getAverageSpeed());
        carStats.setOvertaken(countOvertakenBy(fastestCar));

        return carStats;
    }

    @GetMapping(value = "/speeding/{limit}")
    public List<CarRegistration> getSpeedingCars(@PathVariable("limit") Double limit) {
        List<CarRegistration> speeders = new ArrayList<>();
        for (CarRegistration carRegistration : service.findAll()) {
            if (carRegistration.getAverageSpeed() > limit)
                speeders.add(carRegistration);
        }
        return speeders;
    }

    private CarRegistration findFastestRegistration() {
        CarRegistration fastestCar = null;
        double highestSpeed = 0;
        for (CarRegistration carRegistration : service.findAll()) {
            double speed = carRegistration.getAverageSpeed();
            if (speed > highestSpeed) {
                highestSpeed = speed;
                fastestCar = carRegistration;
            }
        }
        return fastestCar;
    }

    private int countOvertakenBy(CarRegistration checkCar) {
        int overtakenCounter = 0;
        for (CarRegistration carRegistration : service.findAll()) {
            if (carRegistration.getEntranceTime().isBefore(checkCar.getEntranceTime()) && carRegistration.getExitTime().isAfter(checkCar.getExitTime()))
                ++overtakenCounter;
        }
        return overtakenCounter;
    }
}
