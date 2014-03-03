package com.dao;

import java.io.Serializable;
import java.util.List;



import com.model.StatusName;


public class StatusNameDAO extends GenericDAO<StatusName> implements Serializable{

	private static final long serialVersionUID = 1L;

	public StatusNameDAO() {
		super(StatusName.class);
	}


	public List<StatusName> findAllStatusName() {

		

		return super.findAll();

	}





	


}