/**
 * 
 */
package com.caveofprogramming.model;

import org.springframework.data.repository.CrudRepository;

/**
 * @author java_dev
 *
 */
public interface ProfileDao extends CrudRepository<Profile, Long> {
	Profile findByUser(SiteUser user);
}
