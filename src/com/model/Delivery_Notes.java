package com.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@NamedQuery(name = "Delivery_Notes.findDeliveryByIdWithProducts",
query = "select d from Delivery_Notes d left join fetch d.product where d.id = :delivery_notes_id")
@Table(name="delivery_notes", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")
		 })

public class Delivery_Notes implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_DELIVERY_BY_ID_WITH_PRODUCTS = "Delivery_Notes.findDeliveryByIdWithProducts";

	private int id;
	private String delivery_notes_name;
	private Date delivery_date;
	private Supplier_list supplier_list;
	private Projects project;
	private Delivery_list delivery_list;
	private List<Product> products;
	public Delivery_Notes() {
	}

	public Delivery_Notes(String delivery_notes_name,Supplier_list supplier_list) {
		this.delivery_notes_name = delivery_notes_name;
		this.supplier_list=supplier_list;
		

	}

	public Delivery_Notes(String delivery_notes_name,Supplier_list supplier_list,
			List<Product> products) {
		this.delivery_notes_name = delivery_notes_name;
		this.products=products;
		this.supplier_list=supplier_list;
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

	@Column(name = "delivery_notes_name")
	public String getDelivery_notes_name() {
		return this.delivery_notes_name;
	}

	public void setDelivery_notes_name(String delivery_notes_name) {
	
		this.delivery_notes_name = delivery_notes_name;
	}



	@OneToMany(fetch = FetchType.EAGER, mappedBy = "delivery_notes")
	public List<Product> getProduct() {
		return this.products;
	}

	public void setProduct(List<Product> products) {
		this.products = products;
	}

	

	@Column(name = "delivery_date")
	public Date getDelivery_date() {
		if (delivery_date==null){
			delivery_date=Calendar.getInstance().getTime();
		}
		return delivery_date;
	}

	public void setDelivery_date(Date delivery_date) {
		if (delivery_date==null){
			delivery_date=Calendar.getInstance().getTime();
		}
		this.delivery_date = delivery_date;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_code",referencedColumnName="supplier_code", nullable = false)
	public Supplier_list getSupplier_list() {
		
		
		return this.supplier_list;
	}
 
	public void setSupplier_list(Supplier_list supplier_list) {
		this.supplier_list = supplier_list;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id",referencedColumnName="project_id", nullable = false)
	public Projects getProjects() {
		
		
		return this.project;
	}
 
	public void setProjects(Projects project) {
		this.project = project;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_adress_code",referencedColumnName="delivery_adress_code", nullable = false)
	public Delivery_list getDelivery_list() {
		
		
		return this.delivery_list;
	}
 
	public void setDelivery_list(Delivery_list delivery_list) {
		this.delivery_list = delivery_list;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Delivery_Notes) {
			Delivery_Notes delivery_notes = (Delivery_Notes) obj;
			return delivery_notes.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return delivery_notes_name;
	}
	
}