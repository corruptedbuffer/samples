/**
 * 
 */
package com.vb.sample.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author javi-more-garc
 *
 */
@Entity
@Table(name = "company")
public class Company extends AbstractEntity {

	private static final long serialVersionUID = 4899729444255680456L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@NotBlank
	@Column(name = "name", nullable = false)
	private String name;

	@NotBlank
	@Column(name = "address", nullable = false)
	private String address;

	@NotBlank
	@Column(name = "city", nullable = false)
	private String city;

	@NotBlank
	@Column(name = "country", nullable = false)
	private String country;

	@Column(name = "email")
	@Email
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@ManyToMany
	@JoinTable(name = "company_person", joinColumns = {
			@JoinColumn(name = "company_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "person_id", referencedColumnName = "id") })
	private List<Person> employees = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "company_beneficial_owner", joinColumns = {
			@JoinColumn(name = "company_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "beneficial_owner_id", referencedColumnName = "id") })
	private List<Person> beneficialOwners = new ArrayList<>();

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Person> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Person> employees) {
		this.employees = employees;
	}

	public List<Person> getBeneficialOwners() {
		return this.beneficialOwners;
	}

	public void setBeneficialOwners(List<Person> beneficialOwners) {
		this.beneficialOwners = beneficialOwners;
	}

}
