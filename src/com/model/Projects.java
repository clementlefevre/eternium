package com.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="projects", uniqueConstraints = {
		@UniqueConstraint(columnNames = "project_id"),
		@UniqueConstraint(columnNames = "project_name") })

public class Projects implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private int project_id;
	private String project_name;
	private Set<Delivery_Notes> delivery_notes= new HashSet<Delivery_Notes>(
			0);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "project_id", unique = true, nullable = false)
	public Integer getProject_id() {
		return this.project_id;
	}

	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}

	@Column(name = "project_name", unique = true, nullable = false, length = 32)
	public String getProject_name() {
		return this.project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}



	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projects")
	public Set<Delivery_Notes> getDelivery_notes() {
		return delivery_notes;
	}

	public void setDelivery_notes(Set<Delivery_Notes> delivery_notes) {
		this.delivery_notes = delivery_notes;
	}
	@Override
	public int hashCode() {
		return getProject_id();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Projects) {
			Projects project = (Projects) obj;
			return project.getProject_id() == project_id;
		}

		return false;
	}
	

}