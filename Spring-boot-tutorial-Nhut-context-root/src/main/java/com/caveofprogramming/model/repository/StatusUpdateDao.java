/**
 * 
 */
package com.caveofprogramming.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.caveofprogramming.model.entity.StatusUpdate;

/**
 * @author nhut
 *
 */
//public interface StatusUpdateDao extends CrudRepository<StatusUpdate, Long>{
@Repository
public interface StatusUpdateDao extends PagingAndSortingRepository<StatusUpdate, Long>{
	StatusUpdate findFirstByOrderByAddedDesc();	
}
