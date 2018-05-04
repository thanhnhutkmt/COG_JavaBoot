/**
 * 
 */
package com.caveofprogramming.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Random;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.caveofprogramming.App;
import com.caveofprogramming.model.entity.Interest;
import com.caveofprogramming.model.entity.Profile;
import com.caveofprogramming.model.entity.SiteUser;
import com.caveofprogramming.service.FileService;
import com.caveofprogramming.service.InterestService;
import com.caveofprogramming.service.ProfileService;
import com.caveofprogramming.service.UserService;

/**
 * @author java_dev
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
@WebAppConfiguration
@Transactional
public class InterestProfileTest {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private InterestService interestService;
	
	private SiteUser [] users = {
		new SiteUser("a@abc.com", "llllll", "aaaaaa", "ddddd"),
		new SiteUser("b@abc.com", "llllll", "bbbbbb", "ddddd"),
		new SiteUser("c@abc.com", "llllll", "cccccc", "ddddd")
	};
	
	private String[][] interests = {
		{"music", "guitar_xxxxx", "plants"},
		{"music", "music", "philosophy_lkjlkjlkj"},
		{"philosophy_lkjlkjlk", "football"}
	};
	
	@Test
	public void testInterests() {
		for(int i = 0; i < users.length; i++) {
			SiteUser user = users[i];
			String[] interestArray = interests[i];
			userService.register(user);
			HashSet<Interest> interestSet = new HashSet<>();
			
			for(String interestText : interestArray) {
				Interest interest = interestService.createIfNotExists(interestText);
				interestSet.add(interest);
				
				assertNotNull("Interest should not be null", interest);
				assertNotNull("Interest should have ID", interest.getId());
				assertEquals("Test should match", interestText, interest.getName());
			}
			
			Profile profile = new Profile(user);
			profile.setInterests(interestSet);
			profileService.save(profile);
			
			Profile retrievedProfile = profileService.getUserProfile(user);
			assertEquals("Interest sets should match", interestSet, retrievedProfile.getInterests());
		}
	}
}
