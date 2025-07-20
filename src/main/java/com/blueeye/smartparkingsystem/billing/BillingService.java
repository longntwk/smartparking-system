package com.blueeye.smartparkingsystem.billing;

import com.blueeye.smartparkingsystem.event.VehicleExitedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class BillingService {
    private final BillingRecodRepository billingRecodRepository;

    public BillingService(BillingRecodRepository billingRecodRepository) {
        this.billingRecodRepository = billingRecodRepository;
    }
    @EventListener
    public void handleVehicleExit(VehicleExitedEvent event){
        Duration duration = Duration.between(event.entryTime(), event.exitTime());
        double amount = Math.max(20,(duration.toMinutes() / 60)* 50); ///50/h
        BillingRecord record = new BillingRecord(null, event.vehicleNumber(),amount , event.exitTime());
        billingRecodRepository.save(record);
    }
}
