package com.blueeye.smartparkingsystem.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/parking")
public class EntryController {
    @Autowired private EntryService entryService;
    @Autowired private ExitService exitService;

    @PostMapping("/entry")
    public ResponseEntity<String> entry(@RequestParam String vehicleNumber){
        entryService.vehicleEntry(vehicleNumber);
        return ResponseEntity.ok("Vehicle entered: "+vehicleNumber);
    }
    @PostMapping("/exit")
    public ResponseEntity<String> exit(@RequestParam String vehicleNumber){
        exitService.vehicleExit(vehicleNumber);
        return ResponseEntity.ok("Vehicle exited: "+ vehicleNumber);
    }
}
