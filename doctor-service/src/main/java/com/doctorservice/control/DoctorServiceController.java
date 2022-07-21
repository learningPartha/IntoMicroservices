package com.doctorservice.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorServiceController {

	@GetMapping("/doctors")
	public String getDoctor() {
		try {
			Thread.sleep(20000);
		}catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		return "List of Doctors";
	}
	
}
