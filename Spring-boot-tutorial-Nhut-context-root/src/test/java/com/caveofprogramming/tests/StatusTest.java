/**
 * 
 */
package com.caveofprogramming.tests;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.caveofprogramming.App;
import com.caveofprogramming.model.entity.StatusUpdate;
import com.caveofprogramming.model.repository.StatusUpdateDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author nhut
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
@WebAppConfiguration
@Transactional
public class StatusTest {
	@Test
	public void testDummy() {
		Long value = 7L;
		assertNotNull("Value should not be null", value);
		
	}
	
	@Autowired
	private StatusUpdateDao statusUpdateDao;
	
	@Test
	public void testSave() {
		StatusUpdate status = new StatusUpdate("This is a test status update.");
		statusUpdateDao.save(status);
		assertNotNull("Non null ID", status.getId());
		assertNotNull("Non null Date", status.getAdded());
		
		StatusUpdate retrieved = statusUpdateDao.findOne(status.getId());
		assertEquals("Matching StatusUpdate", status, retrieved);
		
	}
	
	@Test
	public void testFindLatest() {
		Calendar calendar = Calendar.getInstance();
		StatusUpdate lastStatusUpdate = null;
		for (int i = 0; i < 10; i++) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			StatusUpdate status = new StatusUpdate("Status update " + i, calendar.getTime());
			statusUpdateDao.save(status);
			lastStatusUpdate = status;
		}
		StatusUpdate retrieved = statusUpdateDao.findFirstByOrderByAddedDesc();
		System.out.println("Latest status update \n" +  lastStatusUpdate + "\n" + retrieved);
		assertEquals("Latest status update",  lastStatusUpdate, retrieved);
	}
}