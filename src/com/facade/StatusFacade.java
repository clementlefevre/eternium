package com.facade;

import java.io.Serializable;
import java.util.List;

import com.dao.StatusDAO;
import com.model.Status;


public class StatusFacade implements Serializable{
	private static final long serialVersionUID = 1L;

	private StatusDAO statusDAO = new StatusDAO();


	public List<Status> listAll() {
		statusDAO.beginTransaction();
		System.out.println("******************************on recharge la liste des status");
		List<Status> result =  statusDAO.findAllStatus();
		
		
		statusDAO.closeTransaction();
		return result;
	}
	
	public Status findMaxStatusofOneProduct(int itemId) {
		statusDAO.beginTransaction();
		Status status = statusDAO.findMaxStatusofOneProduct(itemId);
		statusDAO.closeTransaction();
		return status;
	}

	public void createStatus(Status status) {
		System.out.println("on tente de creer un status");
		statusDAO.beginTransaction();
		System.out.println("demarrage de la tx");
		statusDAO.save(status);
		statusDAO.commitAndCloseTransaction();
	}

	public void updateStatus(Status status) {

		statusDAO.beginTransaction();
	
		
		Status persistedstatus = statusDAO.find(status.getStatus_id());

	
		

		persistedstatus.setStatus_name(status.getStatus_name());
		persistedstatus.setEntryDate(status.getEntryDate());

		


		statusDAO.commitAndCloseTransaction();

	}
	
	public void deleteStatus(Status status){
		statusDAO.beginTransaction();
		Status persistedStatusWithIdOnly = statusDAO.findReferenceOnly(status.getStatus_id());
		statusDAO.delete(persistedStatusWithIdOnly);
		statusDAO.commitAndCloseTransaction();
		
	}
	
	

}