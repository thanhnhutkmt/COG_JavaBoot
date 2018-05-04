/**
 * 
 */
package com.caveofprogramming.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.caveofprogramming.App;
import com.caveofprogramming.model.entity.Interest;
import com.caveofprogramming.model.entity.Profile;
import com.caveofprogramming.model.entity.SiteUser;
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
public class BulkTests {
	@Autowired
	UserService userService;
	
	@Autowired
	InterestService interestService;
	
	@Autowired
	ProfileService profileService;
	
	private static final String hobbiesFile = "/com/caveofprogramming/tests/data/hobbies.txt";
	private static final String namesFile = "/com/caveofprogramming/tests/data/names.txt";
	
	private List<String> loadFile(String filename, int maxLength) throws IOException {
		Path fiePath = new ClassPathResource(filename).getFile().toPath();
		Stream<String> stream = Files.lines(fiePath);		
		// @formatter:off
		List<String> items = stream
			.filter(line -> !line.isEmpty())
			.map(line -> line.trim())
			.filter(line -> line.length() <= maxLength)
			.map(line -> line.substring(0, 1).toUpperCase() + 
				line.substring(1).toLowerCase())
//			.map(line -> line + "eeeee")
//			.forEach(System.out::println);
			.collect(Collectors.toList());
			
		// 	@formatter:on	
		stream.close();
		
		return items;
	}
	
	@Test
	public void createTestData() throws IOException {
		Random random = new Random();
		List<String> names = loadFile(namesFile, 25);
		List<String> interests = loadFile(hobbiesFile, 25);
		
//		for (String s: names) {
//			System.out.println(s);
//		}
//		
//		for (String s: interests) {
//			System.out.println(s);
//		}
		for (int numUsers = 0; numUsers < 200; numUsers++) {
			String firstname = names.get(random.nextInt(names.size()));
			String surname = names.get(random.nextInt(names.size()));
			String email = firstname.toLowerCase() + surname.toLowerCase()
				+ "@a.com";
			
			if (userService.get(email) != null) {
				continue;
			}
			
			String password = "pass" + firstname.toLowerCase();
			password = password.substring(0, Math.min(15, password.length()));
			
			assertTrue(password.length() <= 15);
			SiteUser user = new SiteUser(email, password, firstname, surname);
			user.setEnabled(random.nextInt(5) != 0);
			
//			System.out.println(user);	
			userService.register(user);
			Profile profile = new Profile(user);
			int numberInterests = random.nextInt(7);
			Set<Interest> userInterests = new HashSet<Interest>();
			
			for(int i = 0; i < numberInterests; i++) {
				String interestText = interests.get(random.nextInt(interests.size()));
				Interest interest = interestService.createIfNotExists(interestText);
				userInterests.add(interest);
			}
			
			profile.setInterests(userInterests);
			profileService.save(profile);			
		}
		assertTrue(true);
	}
}
