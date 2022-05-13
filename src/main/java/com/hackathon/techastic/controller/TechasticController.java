package com.hackathon.techastic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hackathon.techastic.services.DiversitySerice;
import com.hackathon.techastic.services.ExcelService;
import com.hackathon.techastic.to.UserDetails;

@Controller
public class TechasticController {

	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private DiversitySerice diversityService;
	
	@GetMapping("/")
	public String performSearchAndDecideDiversity(HttpServletRequest request, HttpServletResponse response) {
		try {
		List<UserDetails> details = excelService.getData();
		//for(UserDetails user : details) {
			diversityService.findDiversity(details.get(0));
		//}
		
			excelService.saveDiversityData(details);
		}catch(Exception e){
			e.getStackTrace();
		}
		
		
		return "Action Performed";
	}
}
