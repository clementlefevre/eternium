package com.facade;

import java.io.Serializable;
import java.util.List;

import com.dao.Delivery_listDAO;
import com.model.Delivery_list;


public class Delivery_listFacade implements Serializable{
	private static final long serialVersionUID = 1L;

	private Delivery_listDAO delivery_listDAO = new Delivery_listDAO();


	public List<Delivery_list> listAll() {

		delivery_listDAO.beginTransaction();
		List<Delivery_list> result = delivery_listDAO.findAll();
		delivery_listDAO.closeTransaction();
		return result;
	}

	public Delivery_list findDelivery_list(int id) {
		delivery_listDAO.beginTransaction();
		Delivery_list delivery_notes = delivery_listDAO.find(id);
		delivery_listDAO.closeTransaction();
		return delivery_notes;
	}

	public void updateDelivery_list(Delivery_list delivery_list) {
		delivery_listDAO.beginTransaction();


		Delivery_list persistedDelivery_list = delivery_listDAO.find(delivery_list.getDelivery_adress_code());

		/*
		persistedDelivery_list.setSupplier_list(delivery_list.getSupplier_list());

		persistedDelivery_list.setDelivery_notes_name(delivery_list.getDelivery_notes_name());
		persistedDelivery_list.setDelivery_date(delivery_list.getDelivery_date());*/



		delivery_listDAO.commitAndCloseTransaction();

	}


	public void deleteDelivery_list(Delivery_list delivery_list){
		delivery_listDAO.beginTransaction();
		Delivery_list persistedDelivery_list = delivery_listDAO.findReferenceOnly(delivery_list.getDelivery_adress_code());
		delivery_listDAO.delete(persistedDelivery_list);
		delivery_listDAO.commitAndCloseTransaction();

	}



	public void createDelivery_list(Delivery_list delivery_list) {

		delivery_listDAO.beginTransaction();
		delivery_listDAO.save(delivery_list);
		delivery_listDAO.commitAndCloseTransaction();
	}


}