/**
 * 
 */
package com.caveofprogramming.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author java_dev
 *
 */
@Repository
public interface InterestDao extends CrudRepository<Interest, String> {
	Interest findOneByName(String name);
}
