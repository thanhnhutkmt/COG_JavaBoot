/**
 * 
 */
package com.caveofprogramming.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.caveofprogramming.model.entity.SiteUser;

/**
 * @author java_dev
 *
 */
@Repository
public interface UserDao extends CrudRepository<SiteUser, Long> {
	SiteUser findByEmail(String email);
}
