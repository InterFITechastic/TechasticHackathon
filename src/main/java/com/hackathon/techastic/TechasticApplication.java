package com.hackathon.techastic;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hackathon.techastic.services.ExcelService;
import com.hackathon.techastic.to.UserDetails;

@SpringBootApplication
public class TechasticApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechasticApplication.class, args);
		
		ExcelService service = new ExcelService();
		try {
		List<UserDetails> details = service.getData();
		for(UserDetails user : details) {
			service.findDiversity(user);
		}
		
		service.saveDiversityData(details);
		}catch(Exception e){
			e.getStackTrace();
		}
	}

}
