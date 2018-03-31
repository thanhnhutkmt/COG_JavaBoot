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
public interface UserDao extends CrudRepository<SiteUser, Long> {
	SiteUser findByEmail(String email);
}
