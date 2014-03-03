package com.model;

import static javax.persistence.GenerationType.IDENTITY;

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
@NamedQuery(name = "Product.findProductByIdWithStatus", query = "select p from Product p left join fetch p.status where p.id = :item_id")
@Table(name = "item_list",
uniqueConstraints = @UniqueConstraint(columnNames = "item_id"))
public class Product implements java.io.Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String FIND_PRODUCT_BY_ID_WITH_STATUS = "Product.findProductByIdWithStatus";
	private Integer item_id;
	private Product_infos product_infos;
	private Delivery_Notes delivery_notes;
	private String serial;
	private String manufacturing_date;
	private Integer quantity_delivered;
	private List<Status> status;
	
 
	public Product() {
	}
 
	
 
	public Product(Product_infos product_infos,
			Delivery_Notes delivery_notes,
			String serial,String manufacturing_date,
			Integer quantity_delivered) {
		this.product_infos = product_infos;
		this.delivery_notes = delivery_notes;
		this.serial = serial;
		this.manufacturing_date = manufacturing_date;
		this.quantity_delivered = quantity_delivered;
	
	}
	public Product(List<Status> status,Product_infos product_infos,
			Delivery_Notes delivery_notes,
			String serial,String manufacturing_date,
			Integer quantity_delivered) {
		this.product_infos = product_infos;
		this.delivery_notes = delivery_notes;
		this.serial = serial;
		this.manufacturing_date = manufacturing_date;
		this.quantity_delivered = quantity_delivered;
		this.status=status;
	
	}
 
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "item_id", unique = true, nullable = false)
	public Integer getItem_id() {
		return this.item_id;
	}
 
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_infos_id", nullable = false)
	public Product_infos getProduct_infos() {
		return this.product_infos;
	}
 
	public void setProduct_infos(Product_infos product_infos) {
		this.product_infos = product_infos;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "delivery_notes_id",referencedColumnName="id" ,nullable = false)
	public Delivery_Notes getDelivery_notes() {
		
		return this.delivery_notes;
	}
 
	public void setDelivery_notes(Delivery_Notes delivery_notes) {
		
		this.delivery_notes = delivery_notes;
	}
	
 
	@Column(name = "serial")
	public String getSerial() {
		return this.serial;
	}
 
	public void setSerial(String serial) {
		this.serial = serial;
	}
 
	@Column(name = "manufacturing_date")
	public String getManufacturing_Date() {
		return this.manufacturing_date;
	}
 
	public void setManufacturing_Date(String manufacturing_date) {
		this.manufacturing_date = manufacturing_date;
	}
 
	@Column(name = "quantity_delivered")
	public Integer getQuantity_delivered() {
		return this.quantity_delivered;
	}
 
	public void setQuantity_delivered(Integer quantity_delivered) {
		this.quantity_delivered = quantity_delivered;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", orphanRemoval=true)
	public List<Status> getStatus() {
		return this.status;
	}
 
	public void setStatus(List<Status> status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		return item_id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			Product product = (Product) obj;
			return product.getItem_id() == item_id;
		}

		return false;
	}

	
	
	
 
}