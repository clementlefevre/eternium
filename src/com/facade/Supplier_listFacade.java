package com.facade;

import java.io.Serializable;
import java.util.List;

import com.dao.Supplier_listDAO;
import com.model.Supplier_list;


public class Supplier_listFacade implements Serializable{
	private static final long serialVersionUID = 1L;

	private  Supplier_listDAO  supplier_listDAO = new Supplier_listDAO();


	public List<Supplier_list> listAll() {

		supplier_listDAO.beginTransaction();
		List<Supplier_list> result = supplier_listDAO.findAll();
		supplier_listDAO.closeTransaction();
		return result;
	}

	public Supplier_list findDelivery_Notes(int id) {
		supplier_listDAO.beginTransaction();
		Supplier_list supplier_list = supplier_listDAO.find(id);
		supplier_listDAO.closeTransaction();
		return supplier_list;
	}

	public void updateDelivery_Notes(Supplier_list supplier_list) {
		supplier_listDAO.beginTransaction();


		Supplier_list persistedSupplier_list = supplier_listDAO.find(supplier_list.getSupplier_code());




		persistedSupplier_list.setSupplier_code(supplier_list.getSupplier_code());




		supplier_listDAO.commitAndCloseTransaction();

	}


	public void deleteSupplier_list(Supplier_list supplier_list){
		supplier_listDAO.beginTransaction();
		Supplier_list persistedDelivery_Notes = supplier_listDAO.findReferenceOnly(supplier_list.getSupplier_code());
		supplier_listDAO.delete(persistedDelivery_Notes);
		supplier_listDAO.commitAndCloseTransaction();

	}
	public Supplier_list findSupplier_list(int id) {
		supplier_listDAO.beginTransaction();
		Supplier_list supplier_list = supplier_listDAO.find(id);
		supplier_listDAO.closeTransaction();
		return supplier_list;
	}


}