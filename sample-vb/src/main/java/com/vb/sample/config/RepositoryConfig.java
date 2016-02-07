/**
 * 
 */
package com.vb.sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.vb.sample.model.Company;
import com.vb.sample.model.Person;

/**
 * @author javi-more-garc
 *
 */
@Configuration
public class RepositoryConfig extends RepositoryRestMvcConfiguration {

	@Value("${spring.data.rest.base-path}")
	private String basePath;

	@Override
	protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.setBasePath(basePath);

		config.exposeIdsFor(Company.class, Person.class);
	}
}
