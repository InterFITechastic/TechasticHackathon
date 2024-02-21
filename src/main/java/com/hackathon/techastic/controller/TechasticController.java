package com.hackathon.techastic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.techastic.services.DiversityService;
import com.hackathon.techastic.services.ExcelService;
import com.hackathon.techastic.to.UserDetails;

@RestController
public class TechasticController {

	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private DiversityService diversityService;

	
	@Value("${my.secret}")
	String keyV;
	
	@GetMapping("/diversity")
	public List<UserDetails> performSearchAndDecideDiversity(HttpServletRequest request, HttpServletResponse response) {
		List<UserDetails> details = null;
		try {
			details = excelService.readUserDetails();
			for (UserDetails user : details) {
				diversityService.findDiversity(user);
			}
			excelService.saveDiversityData(details);
		} catch (Exception e) {
			e.getStackTrace();
		}

		return details;
	}

	@GetMapping("/getAzurekeyvault")
	public String getMyKeyValut(){
		return keyV;
	}
}
