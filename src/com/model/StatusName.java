package com.model;

import static javax.persistence.GenerationType.IDENTITY;

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
@Table(name = "status_list", 
uniqueConstraints = {@UniqueConstraint(columnNames = "status_code")})
public class StatusName implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int status_code;
	public String status_name;
	public Set<Status> status= new HashSet<Status>(
			0);

	public StatusName() {
	}

	/*public Product(Stock stock, Date date) {
		this.stock = stock;
		this.date = date;
	}*/

	public StatusName(String status_name) {
		this.status_name = status_name;
	}

	public StatusName(String status_name,
			Set<Status> status) {
		this.status_name = status_name;
		this.status = status;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "status_code", unique = true, nullable = false)
	public Integer getStatus_code() {
		return this.status_code;
	}
	public void setStatus_code(Integer status_code) {
		this.status_code = status_code;
	}
	
	
	@Column(name = "status_name", unique = true, nullable = false, length = 32)
	public String getStatus_name() {
		return this.status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status_name")
	public Set<Status> getStatus() {
		return this.status;
	}

	public void setStatus(Set<Status> status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		return getStatus_code();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StatusName) {
			StatusName statusName = (StatusName) obj;
			return statusName.getStatus_code() == status_code;
		}

		return false;
	}


}