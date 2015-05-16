/**
 * 
 */
package com.jmg.sample.server;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Javier Moreno Garcia
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// note that by extending JpaRepository you get "for free" most of CRUD
	// operations but that does not mean you can define your own finders (select
	// .. from ..), updaters (Update..) or remores (delete from...)
}
