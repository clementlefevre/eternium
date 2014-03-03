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
@Table(name="supplier_list", uniqueConstraints = {
		@UniqueConstraint(columnNames = "supplier_code"),
		@UniqueConstraint(columnNames = "supplier_name") })

public class Supplier_list implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private int supplier_code;
	private String supplier_name;
	private Set<Product_infos> product_infos= new HashSet<Product_infos>(
			0);
	private Set<Delivery_Notes> delivery_notes= new HashSet<Delivery_Notes>(
			0);

	/*public Supplier_list() {
	}

	public Supplier_list(String supplier_name,Set<Delivery_Notes> delivery_notes) {
		this.supplier_name = supplier_name;
		this.delivery_notes= delivery_notes;
	}

	public Supplier_list(String supplier_name,
			Set<Product_infos> product_infos,Set<Delivery_Notes> delivery_notes) {
		this.supplier_name = supplier_name;
		this.product_infos = product_infos;
		this.delivery_notes= delivery_notes;
	}*/

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "supplier_code", unique = true, nullable = false)
	public Integer getSupplier_code() {
		return this.supplier_code;
	}

	public void setSupplier_code(Integer supplier_code) {
		this.supplier_code = supplier_code;
	}

	@Column(name = "supplier_name", unique = true, nullable = false, length = 32)
	public String getSupplier_name() {
		return this.supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}



	@OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier_list")
	public Set<Product_infos> getProduct_infos() {
		return this.product_infos;
	}

	public void setProduct_infos(Set<Product_infos> product_infos) {
		this.product_infos = product_infos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier_list")
	public Set<Delivery_Notes> getDelivery_notes() {
		return delivery_notes;
	}

	public void setDelivery_notes(Set<Delivery_Notes> delivery_notes) {
		this.delivery_notes = delivery_notes;
	}
	@Override
	public int hashCode() {
		return getSupplier_code();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Supplier_list) {
			Supplier_list supplier_list = (Supplier_list) obj;
			return supplier_list.getSupplier_code() == supplier_code;
		}

		return false;
	}
	

}