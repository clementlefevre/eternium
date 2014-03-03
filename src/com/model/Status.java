package com.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@NamedQuery(name = "Status.findMaxStatusforOneProduct", query = "SELECT s FROM Status s WHERE s.entryDate = (SELECT MAX(ss.entryDate) FROM Status ss WHERE (ss.product = s.product AND s.product.id = :item_id))")
@Table(name = "status",  
uniqueConstraints = @UniqueConstraint(columnNames = "status_id"))
public class Status implements java.io.Serializable,Comparable<Status> {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String FIND_MAX_STATUS_FOR_ONE_PRODUCT = "Status.findMaxStatusforOneProduct";
	private Integer status_id;
	private String comment;
	private String revision_code;
	private Date entryDate;
	private Product product;
	private StatusName status_name;
	private Loc loc;
	
	
	
 
	public Status() {
	}
 
	
 
	public Status(StatusName status_name,String comment,Product product) {
		this.status_name = status_name;
		this.comment = comment;
		this.product=product;
		
	}
 
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "status_id", unique = true, nullable = false)
	public Integer getStatus_id() {
		return this.status_id;
	}
 
	public void setStatus_id(Integer item_id) {
		this.status_id = item_id;
	}
 
	
 
	@Column(name = "comment")
	public String getComment() {
		return this.comment;
	}
 
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Column(name = "revision_code")
	public String getRevision_code() {
		return this.revision_code;
	}
 
	public void setRevision_code(String revision_code) {
		this.revision_code = revision_code;
	}
	
	@Column(name = "status_entry_date")
	public Date getEntryDate() {
		if (entryDate==null){
			entryDate=Calendar.getInstance().getTime();
		}
		return this.entryDate;
	}
 
	public void setEntryDate(Date entryDate) {
		if (entryDate==null){
			entryDate=Calendar.getInstance().getTime();
		}
		this.entryDate = entryDate;
	}
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_code", nullable = false)
	public StatusName getStatus_name() {
		return this.status_name;
	}
 
	public void setStatus_name(StatusName status_name) {
		this.status_name = status_name;
	}
	@ManyToOne(fetch = FetchType.LAZY  )
	@JoinColumn(name = "item_id", nullable = false)
	public Product getProduct() {
		return this.product;
	}
 
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loc_id",referencedColumnName="loc_id", nullable = true)
	public Loc getLoc() {
		
		
		return this.loc;
	}
 
	public void setLoc(Loc loc) {
		this.loc = loc;
	}
	
	/*@Override
	public int compareTo(Status o) {
		int result =(product.getItem_id() - o.product.getItem_id());
		 if(result==0) {
		        return entryDate.compareTo(o.entryDate);
		    }
		    else {
		        return result;
		    }
		}*/
	 @Override
	  public int compareTo(Status o) {
	    return getEntryDate().compareTo(o.getEntryDate());
	  }
	 
	 @Override
		public int hashCode() {
			return status_id;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Status) {
				Status status = (Status) obj;
				return status.getStatus_id() == status_id;
			}

			return false;
		}
		
		
 
}