package com.facade;

import java.io.Serializable;
import java.util.List;

import com.dao.StatusNameDAO;
import com.model.StatusName;


public class StatusNameFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private StatusNameDAO statusNameDAO = new StatusNameDAO();

	
	public List<StatusName> listAll() {
	
		statusNameDAO.beginTransaction();
		List<StatusName> result = statusNameDAO.findAll();
		statusNameDAO.closeTransaction();
		return result;
	}
	public StatusName findStatusName(int id) {
		statusNameDAO.beginTransaction();
		StatusName status_name = statusNameDAO.find(id);
		statusNameDAO.closeTransaction();
		return status_name;
	}

}