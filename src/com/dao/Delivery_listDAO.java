package com.dao;

import java.io.Serializable;
import java.util.List;

import com.model.Delivery_list;


public class Delivery_listDAO extends GenericDAO<Delivery_list> implements Serializable{

	private static final long serialVersionUID = 1L;

	public Delivery_listDAO() {
		super(Delivery_list.class);
	}


	public List<Delivery_list> findAll() {

		
		return super.findAll();

	}

	public void delete(Delivery_list delivery_list) {
    	super.delete(delivery_list.getDelivery_adress_code(), Delivery_list.class);
}



	


}