/**
 * 
 */
package com.caveofprogramming.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.caveofprogramming.model.entity.Interest;

/**
 * @author java_dev
 *
 */
@Repository
public interface InterestDao extends CrudRepository<Interest, String> {
	Interest findOneByName(String name);
}
