package com.facade;

import java.io.Serializable;
import java.util.List;

import com.dao.Train_typeDAO;
import com.model.Train_type;


public class Train_typeFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Train_typeDAO train_typeDAO = new Train_typeDAO();

	
	public List<Train_type> listAll() {
	
		train_typeDAO.beginTransaction();
		List<Train_type> result = train_typeDAO.findAll();
		train_typeDAO.closeTransaction();
		return result;
	}
	
	public List<Train_type> listAllTrain_type_family() {
		
		train_typeDAO.beginTransaction();
		List<Train_type> result = train_typeDAO.findAllTrain_family();
		train_typeDAO.closeTransaction();
		return result;
	}
	
public List<Train_type> listAllTrain_type_label() {
		
		train_typeDAO.beginTransaction();
		List<Train_type> result = train_typeDAO.findAllTrain_label();
		train_typeDAO.closeTransaction();
		return result;
	}
	
	
	public Train_type findTrain_type(int id) {
		train_typeDAO.beginTransaction();
		Train_type train_type = train_typeDAO.find(id);
		train_typeDAO.closeTransaction();
		return train_type;
	}

}