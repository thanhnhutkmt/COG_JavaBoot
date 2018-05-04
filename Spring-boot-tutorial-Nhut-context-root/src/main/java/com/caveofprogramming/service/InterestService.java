/**
 * 
 */
package com.caveofprogramming.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caveofprogramming.model.entity.Interest;
import com.caveofprogramming.model.repository.InterestDao;

/**
 * @author java_dev
 *
 */
@Service
public class InterestService {
	@Autowired
	private InterestDao interestDao;
	
	public long count() {
		return interestDao.count();
	}
	
	public Interest get(String name) {
		return interestDao.findOneByName(name);
	}
	
	public void save(Interest i) {
		interestDao.save(i);
	}
	
//	@Transactional
	public Interest createIfNotExists(String InterestText) {
		Interest interest = interestDao.findOneByName(InterestText);
		if (interest == null) {
			interest = new Interest(InterestText);
			interestDao.save(interest);
		}		
		return interest;			
	}
}
