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
public interface VerificationDao extends CrudRepository<VerificationToken, Long> {
	VerificationToken findByToken(String token);
}
