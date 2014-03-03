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
@Table(name="delivery_list", uniqueConstraints = {
		@UniqueConstraint(columnNames = "delivery_adress_code") })

public class Delivery_list implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private int delivery_adress_code;
	private String delivery_adress;
	private String delivery_adress_sap_code;
	private Set<Delivery_Notes> delivery_notes= new HashSet<Delivery_Notes>(
			0);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "delivery_adress_code", unique = true, nullable = false)
	public Integer getDelivery_adress_code() {
		return this.delivery_adress_code;
	}

	public void setDelivery_adress_code(Integer delivery_adress_code) {
		this.delivery_adress_code = delivery_adress_code;
	}

	@Column(name = "delivery_adress", unique = true, nullable = false, length = 32)
	public String getDelivery_adress() {
		return this.delivery_adress;
	}

	public void setDelivery_adress_sap_code(String delivery_adress_sap_code) {
		this.delivery_adress_sap_code = delivery_adress_sap_code;
	}
	@Column(name = "delivery_adress_sap_code", unique = true, nullable = false, length = 32)
	public String getDelivery_adress_sap_code() {
		return this.delivery_adress_sap_code;
	}

	public void setDelivery_adress(String delivery_adress) {
		this.delivery_adress = delivery_adress;
	}
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery_list")
	public Set<Delivery_Notes> getDelivery_notes() {
		return delivery_notes;
	}

	public void setDelivery_notes(Set<Delivery_Notes> delivery_notes) {
		this.delivery_notes = delivery_notes;
	}
	@Override
	public int hashCode() {
		return getDelivery_adress_code();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Delivery_list) {
			Delivery_list delivery_list = (Delivery_list) obj;
			return delivery_list.getDelivery_adress_code() == delivery_adress_code;
		}

		return false;
	}
	

}