/**
 * 
 */
package com.caveofprogramming.model.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.caveofprogramming.model.dto.SearchResult;
import com.caveofprogramming.model.entity.Profile;
import com.caveofprogramming.model.entity.SiteUser;

/**
 * @author java_dev
 *
 */
@Repository
public interface ProfileDao extends CrudRepository<Profile, Long> {
	Profile findByUser(SiteUser user);

//	List<Profile> findByInterestsName(String text);
	// for more details, have a look here (Java GrandMaster Nhut)
	//	https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html#repositories.special-parameters
	List<Profile> findByInterestsNameContainingIgnoreCase(String text);
	Page<Profile> findDistinctByInterestsNameContainingIgnoreCase(String text, Pageable request);
}
