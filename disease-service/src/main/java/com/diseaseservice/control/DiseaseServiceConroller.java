package com.diseaseservice.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiseaseServiceConroller {

	@GetMapping("/diseases")
	public String getDisease() {
		return "List of Diseases";
	}
	
}
