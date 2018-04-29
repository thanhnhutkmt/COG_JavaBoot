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
public interface ProfileDao extends CrudRepository<Profile, Long> {
	Profile findByUser(SiteUser user);
}
