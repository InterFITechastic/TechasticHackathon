package com.hackathon.techastic.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hackathon.techastic.to.UserDetails;
import com.hackathon.techastic.util.TechasticUtil;

@Service
public class DiversitySerice {


public void findDiversity(UserDetails user) throws Exception {
		
		checkPublicData(user);
		if(user.isWomanOwned() == false || user.getMinorityOwnedDesc() == null) {
			checkLinkedIn(user);
		}
		
		if(user.isWomanOwned() == false || user.getMinorityOwnedDesc() == null) {
			checkOtherSites(user);
		}
		
	}
	
	public void checkPublicData(UserDetails user) throws Exception {
		List<String> linksFromGoogle = TechasticUtil.searchGoogle(user);
		//for(String url : linksFromGoogle) {
			String ownerContainedText = TechasticUtil.searchSiteForKeyWord(linksFromGoogle.get(0), "cfo");
			if(ownerContainedText != null) {
				System.out.println("============Diversified====================="+ ownerContainedText);
			}
		//}
	}
	
	/**
	 * 
	 * @param user
	 */
	public void checkLinkedIn(UserDetails user) {
		
	}
	
	/**
	 * 
	 * @param details
	 * @throws IOException
	 */
	public void checkOtherSites(UserDetails user) {
		
	}
	
}
