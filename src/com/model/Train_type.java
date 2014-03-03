package com.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity

@Table(name="train_type", uniqueConstraints = {
		@UniqueConstraint(columnNames = "train_type_id")
		 })

public class Train_type implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int train_type_id;
	private String train_label;
	private String train_family;
	
	
	
	private List<Loc> loc;
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "train_type_id", unique = true, nullable = false)
	public Integer getTrain_type_id() {
		return this.train_type_id;
	}

	public void setTrain_type_id(Integer train_type_id) {
		this.train_type_id = train_type_id;
	}

	@Column(name = "train_label")
	public String getTrain_label() {
		return this.train_label;
	}

	public void setTrain_label(String train_label) {
	
		this.train_label = train_label;
	}
	@Column(name = "train_family")
	public String getTrain_family() {
		return this.train_family;
	}

	public void setTrain_family(String train_family) {
	
		this.train_family = train_family;
	}



	@OneToMany(fetch = FetchType.LAZY, mappedBy = "train_type")
	public List<Loc> getLoc() {
		return this.loc;
	}

	public void setLoc(List<Loc> loc) {
		this.loc = loc;
	}

	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Train_type) {
			Train_type train_type = (Train_type) obj;
			return train_type.getTrain_type_id() == train_type_id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return train_label;
	}
	
}