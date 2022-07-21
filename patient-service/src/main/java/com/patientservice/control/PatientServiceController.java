package com.patientservice.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientServiceController {

	@GetMapping("/patients")
	public String getPatient() {
		return "List of patients";
	}
	
}
