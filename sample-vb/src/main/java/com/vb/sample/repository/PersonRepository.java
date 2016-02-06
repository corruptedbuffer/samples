/**
 * 
 */
package com.vb.sample.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.vb.sample.model.Person;

/**
 * 
 * @author javi-more-garc
 *
 */
@RepositoryRestResource(collectionResourceRel = "person", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

}