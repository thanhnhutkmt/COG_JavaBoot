/**
 * 
 */
package com.caveofprogramming.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author nhut
 *
 */
//public interface StatusUpdateDao extends CrudRepository<StatusUpdate, Long>{
public interface StatusUpdateDao extends PagingAndSortingRepository<StatusUpdate, Long>{
	StatusUpdate findFirstByOrderByAddedDesc();	
}
