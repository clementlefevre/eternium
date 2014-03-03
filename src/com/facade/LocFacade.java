package com.facade;

import java.io.Serializable;
import java.util.List;

import com.dao.LocDAO;
import com.model.Loc;


public class LocFacade implements Serializable{
	private static final long serialVersionUID = 1L;

	private LocDAO locDAO = new LocDAO();


	public List<Loc> listAll() {

		locDAO.beginTransaction();
		List<Loc> result = locDAO.findAll();
		locDAO.closeTransaction();
		return result;
	}
	public Loc findLoc(int id) {
		locDAO.beginTransaction();
		Loc loc = locDAO.find(id);
		locDAO.closeTransaction();
		return loc;
	}


	public void updateLoc(Loc loc) {
		locDAO.beginTransaction();


		Loc persistedLoc = locDAO.find(loc.getLoc_id());


		persistedLoc.setLoc_serial_number(loc.getLoc_serial_number());
		persistedLoc.setStatus(loc.getStatus());
		persistedLoc.setTrain_type(loc.getTrain_type());


		locDAO.commitAndCloseTransaction();

	}


	public void deleteLoc(Loc loc){
		locDAO.beginTransaction();
		Loc persistedLoc = locDAO.findReferenceOnly(loc.getLoc_id());
		locDAO.delete(persistedLoc);
		locDAO.commitAndCloseTransaction();

	}

	public Loc findLocWithAllProducts(int id) {
		locDAO.beginTransaction();
		Loc loc = locDAO.findLocWithAllProducts(id);
		locDAO.closeTransaction();
		return loc;
	}

	public void createLoc(Loc loc) {

		locDAO.beginTransaction();
		locDAO.save(loc);
		locDAO.commitAndCloseTransaction();
	}


}