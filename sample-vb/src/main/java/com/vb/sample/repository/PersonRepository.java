/**
 * 
 */
package com.vb.sample.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.vb.sample.model.Person;

/**
 * 
 * @author javi-more-garc
 *
 */
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	@RestResource(path = "firstNameOrLastNameContains", rel = "firstNameOrLastNameContains")
	public List<Person> findByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(@Param("firstName") String firstName,
			@Param("lastName") String lastName);

}