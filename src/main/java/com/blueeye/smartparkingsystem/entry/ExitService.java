package com.blueeye.smartparkingsystem.entry;

import com.blueeye.smartparkingsystem.event.VehicleEnteredEvent;
import com.blueeye.smartparkingsystem.event.VehicleExitedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExitService {
    private final ParkingEntryRepository repository;
    private final ApplicationEventPublisher publisher;

    public ExitService(ParkingEntryRepository repository, ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public void vehicleExit(String vehicleNumber){
        // get vehicel entry details from db
        // update exit time
        // save to db
        // publish vehicle exited event
        ParkingEntry parkingEntry = repository.findByVehicleNumberAndActiveTrue(vehicleNumber)
                .orElseThrow(()-> new RuntimeException("No Active entry found for vehicle"));
        parkingEntry.setExitTime(LocalDateTime.now());
        parkingEntry.setActive(false);
        repository.save(parkingEntry);
        publisher.publishEvent(new VehicleExitedEvent(vehicleNumber, parkingEntry.getEntryTime()));
    }
}
