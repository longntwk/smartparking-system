package com.blueeye.smartparkingsystem;

import com.blueeye.smartparkingsystem.allocation.Slot;
import com.blueeye.smartparkingsystem.allocation.SlotRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartparkingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartparkingSystemApplication.class, args);
	}
	@Bean
	CommandLineRunner initSlot(SlotRepository repo){
		return args -> {
            if (repo.count() == 0) {
                repo.save(new Slot(null, "A1", true, null));
				repo.save(new Slot(null, "B1", true, null));
				repo.save(new Slot(null, "C1", true, null));
            }
        };
	}

}
