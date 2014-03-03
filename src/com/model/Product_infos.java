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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="item_infos", uniqueConstraints = {
		@UniqueConstraint(columnNames = "item_infos_id"),
		@UniqueConstraint(columnNames = "item_number") })

public class Product_infos implements Serializable {

	private static final long serialVersionUID = 1L;

	
	
	private int item_infos_id;
	private String item_number;
	private String item_name;
	private String item_german_name;
	private Supplier_list supplier_list;
	
	private Item_family item_family;
	private Set<Product> products = new HashSet<Product>(
			0);

	public Product_infos() {
	}
 
	public Product_infos(String item_number, String item_name,
			Supplier_list supplier_list,
			Item_family item_family) {
		this.item_number = item_number;
		this.item_name = item_name;
		this.supplier_list=supplier_list;
		this.item_family=item_family;
	}
 
	public Product_infos(String item_number, String item_name,
			Set<Product> products) {
		this.item_number = item_number;
		this.item_name = item_name;
		this.products = products;
	}
 
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "item_infos_id", unique = true, nullable = false)
	public Integer getItem_infos_Id() {
		return this.item_infos_id;
	}
 
	public void setItem_infos_Id(Integer item_infos_id) {
		this.item_infos_id = item_infos_id;
	}
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_code", nullable = false)
	public Supplier_list getSupplier_list() {
		return this.supplier_list;
	}
 
	public void setSupplier_list(Supplier_list supplier_list) {
		this.supplier_list = supplier_list;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_family", nullable = false)
	public Item_family getItem_family() {
		return this.item_family;
	}
 
	public void setItem_family(Item_family item_family) {
		this.item_family = item_family;
	}
	
	
	
	@Column(name = "item_number", unique = true, nullable = false, length = 10)
	public String getItem_number() {
		
		return this.item_number;
	}
 
	public void setItem_number(String item_number) {
		
		this.item_number = item_number;
	}
 
	@Column(name = "item_name", unique = false, nullable = false, length = 60)
	public String getItem_name() {
		return this.item_name;
	}
 
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	
	@Column(name = "item_german_name", unique = false, nullable = false, length = 60)
	public String getItem_german_name() {
		return this.item_german_name;
	}
 
	public void setItem_german_name(String item_german_name) {
		this.item_german_name = item_german_name;
	}
	
	
 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product_infos")
	public Set<Product> getProduct() {
		return this.products;
	}
 
	public void setProduct(Set<Product> products) {
		this.products = products;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product_infos) {
			Product_infos product_infos = (Product_infos) obj;
			return product_infos.getItem_infos_Id() == item_infos_id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return item_name;
	}

	
 
}