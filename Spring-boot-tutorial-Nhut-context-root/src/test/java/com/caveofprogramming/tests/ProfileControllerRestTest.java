/**
 * 
 */
package com.caveofprogramming.tests;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.security.core.Authentication;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.caveofprogramming.App;
import com.caveofprogramming.model.Interest;
import com.caveofprogramming.model.Profile;
import com.caveofprogramming.model.SiteUser;
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
public class ProfileControllerRestTest {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private InterestService interestService;
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	@WithMockUser(username="abc@abc.com")
	public void testSaveAndDeleteInterest() throws Exception {
		String interestText = "some interest_here";
		mockMvc.perform(post("/save-interest").param("name", interestText))
			.andExpect(status().isOk());
		Interest interest = interestService.get(interestText);
		assertNotNull("Interest should exist", interest);
		assertEquals("Retrieved interest text should match", interestText, interest.getName());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		SiteUser user = userService.get(email);
		Profile profile = profileService.getUserProfile(user);
		
		assertTrue("Profile should contain interest", 
			profile.getInterests().contains(new Interest(interestText)));
		mockMvc.perform(post("/delete-interest").param("name", interestText))
			.andExpect(status().isOk());
		profile = profileService.getUserProfile(user);
		assertFalse("Profile should not contain interest", 
			profile.getInterests().contains(new Interest(interestText)));
	}
}
