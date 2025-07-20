package com.blueeye.smartparkingsystem.allocation;

import com.blueeye.smartparkingsystem.event.VehicleEnteredEvent;
import com.blueeye.smartparkingsystem.event.VehicleExitedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class SlotAllocationService {
    private final SlotRepository slotRepository;

    public SlotAllocationService(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }


    @EventListener
    public void handleVehicleEntry(VehicleEnteredEvent  event){
        //find the available slot to allocate
        Slot slot = slotRepository.findFirstByAvailableTrue()
                .orElseThrow(()-> new RuntimeException("No available slots!"));
        slot.setAvailable(false);
        slot.setVehicleNumber(event.vehicleNumber());
        //then update the slot status db
        slotRepository.save(slot);
        System.out.println("Allocated Slot "+slot.getSlotCode()+" to vehicle "+event.vehicleNumber());
    }
    @EventListener
    public void handleVehicleExit(VehicleExitedEvent event){
       slotRepository.findByVehicleNumber(event.vehicleNumber())
               .ifPresentOrElse(slot ->{
                   slot.setAvailable(true);
                   slot.setVehicleNumber(null);
                   slotRepository.save(slot);
                   System.out.println("Freed slot "+slot.getSlotCode()+" from vehicle"+ event.vehicleNumber());
               }, ()->{
                   throw new RuntimeException("No Slot found for vehicle "+event.vehicleNumber());
               });
    }

}
