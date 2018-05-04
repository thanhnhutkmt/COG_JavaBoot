/**
 * 
 */
package com.caveofprogramming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.caveofprogramming.model.entity.Profile;
import com.caveofprogramming.model.entity.SiteUser;
import com.caveofprogramming.model.repository.ProfileDao;

/**
 * @author java_dev
 *
 */
@Service
public class ProfileService {
	@Autowired
	ProfileDao profileDao;
	
//	@PreAuthorize("isAuthenticated()")
	public void save(Profile profile) {
		profileDao.save(profile);		
	}
	
//	@PreAuthorize("isAuthenticated()")
	public Profile getUserProfile(SiteUser user) {
		System.out.println("========================");
		System.out.println(user);
		System.out.println("========================");
		return profileDao.findByUser(user);				
	}
}
