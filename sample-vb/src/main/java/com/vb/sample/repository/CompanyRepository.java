/**
 * 
 */
package com.vb.sample.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.vb.sample.model.Company;

/**
 * 
 * @author javi-more-garc
 *
 */
@RepositoryRestResource(collectionResourceRel = "company", path = "companies")
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {

}
