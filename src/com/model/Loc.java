package com.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
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
@NamedQuery(name = "Loc.findLocByIdWithProducts",
query = "select l from Loc l left join fetch l.status s where (l.id = :loc_id) and s.entryDate = (SELECT MAX(ss.entryDate) FROM Status ss WHERE ss.product = s.product)")
@Table(name="loc", uniqueConstraints = {
		@UniqueConstraint(columnNames = "loc_id")
		 })

public class Loc implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_LOC_BY_ID_WITH_PRODUCTS = "Loc.findLocByIdWithProducts";

	private int loc_id;
	private String loc_serial_number;
	
	
	private Train_type train_type;
	
	private List<Status> status;
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "loc_id", unique = true, nullable = false)
	public Integer getLoc_id() {
		return this.loc_id;
	}

	public void setLoc_id(Integer loc_id) {
		this.loc_id = loc_id;
	}

	@Column(name = "loc_serial_number")
	public String getLoc_serial_number() {
		return this.loc_serial_number;
	}

	public void setLoc_serial_number(String loc_serial_number) {
	
		this.loc_serial_number = loc_serial_number;
	}



	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loc")
	public List<Status> getStatus() {
		return this.status;
	}

	public void setStatus(List<Status> status) {
		this.status = status;
	}

	

	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "train_type",referencedColumnName="train_type_id", nullable = false)
	public Train_type getTrain_type() {
		
		
		return this.train_type;
	}
 
	public void setTrain_type(Train_type train_type) {
		this.train_type = train_type;
	}
	
	
	@Override
	public int hashCode() {
		return loc_id;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Loc) {
			Loc loc = (Loc) obj;
			return loc.getLoc_id() == loc_id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return loc_serial_number;
	}
	
}