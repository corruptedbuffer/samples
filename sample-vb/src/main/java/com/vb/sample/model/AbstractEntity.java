package com.vb.sample.model;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author javi-more-garc
 *
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 5432943504547872927L;

	@JsonIgnore
	@Column(name = "created_date", nullable = false)
	@Temporal(TIMESTAMP)
	private Date createdDate;

	@JsonIgnore
	@Column(name = "last_modified_date")
	@Temporal(TIMESTAMP)
	private Date lastModifiedDate;

	@PrePersist
	protected void onCreate() {
		createdDate = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		lastModifiedDate = new Date();
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

}
