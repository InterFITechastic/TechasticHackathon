package com.hackathon.techastic.services;

import java.io.IOException;
import org.springframework.stereotype.Service;

import com.hackathon.techastic.to.UserDetails;
import com.hackathon.techastic.util.TechasticUtil;

@Service
public class DiversityService {


public void findDiversity(UserDetails user) throws Exception {
		
		checkPublicData(user);
		if(user.isWomanOwned() == false || user.getMinorityOwnedDesc() == null) {
			checkLinkedIn(user);
		}
		
		if(user.isWomanOwned() == false || user.getMinorityOwnedDesc() == null) {
			checkOtherSites(user);
		}
		
	}
	
	private void checkPublicData(UserDetails user) throws Exception {
		TechasticUtil.searchGoogle(user);
	}
	
	/**
	 * 
	 * @param user
	 */
	private void checkLinkedIn(UserDetails user) {
		
	}
	
	/**
	 * 
	 * @param details
	 * @throws IOException
	 */
	private void checkOtherSites(UserDetails user) {
		
	}
	
}
