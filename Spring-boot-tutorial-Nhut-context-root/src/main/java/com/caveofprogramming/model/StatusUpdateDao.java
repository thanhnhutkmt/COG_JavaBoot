/**
 * 
 */
package com.caveofprogramming.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nhut
 *
 */
//public interface StatusUpdateDao extends CrudRepository<StatusUpdate, Long>{
@Repository
public interface StatusUpdateDao extends PagingAndSortingRepository<StatusUpdate, Long>{
	StatusUpdate findFirstByOrderByAddedDesc();	
}
