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
@Table(name = "item_family", 
uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Item_family implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private Set<Product_infos> product_infos= new HashSet<Product_infos>(
			0);

	public Item_family() {
	}

	/*public Product(Stock stock, Date date) {
		this.stock = stock;
		this.date = date;
	}*/

	public Item_family(String name) {
		this.name = name;
	}

	public Item_family(String name,
			Set<Product_infos> product_infos) {
		this.name = name;
		this.product_infos = product_infos;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@Column(name = "name", unique = true, nullable = false, length = 32)
	public String getFamily_name() {
		return this.name;
	}
	public void setFamily_name(String name) {
		this.name = name;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item_family")
	public Set<Product_infos> getProduct_infos() {
		return this.product_infos;
	}

	public void setProduct_infos(Set<Product_infos> product_infos) {
		this.product_infos = product_infos;
	}
	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item_family) {
			Item_family item_family = (Item_family) obj;
			return item_family.getId() == id;
		}

		return false;
	}

}