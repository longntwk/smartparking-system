package com.blueeye.smartparkingsystem.notification;

import com.blueeye.smartparkingsystem.event.VehicleEnteredEvent;
import com.blueeye.smartparkingsystem.event.VehicleExitedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @EventListener
    public void notifiOnVehicleEntry(VehicleEnteredEvent event){
        System.out.println("Notification: Vehicle "+ event.vehicleNumber()  +
                "entered at "+ event.entryTime()  + " Welcome!");
    }
    @EventListener
    public void notifiOnVehicleExit(VehicleExitedEvent event){
        System.out.println("Notification: Vehicle "+ event.vehicleNumber()+" has exited.");
    }
}
