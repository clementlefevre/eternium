package com.dao;

import java.io.Serializable;
import java.util.List;

import com.model.Train_type;


public class Train_typeDAO  extends GenericDAO<Train_type>  implements Serializable{

	private static final long serialVersionUID = 1L;

	public Train_typeDAO() {
		super(Train_type.class);
	}


	public List<Train_type> findAllTrain_type() {

		

		return super.findAll();

	}





	


}