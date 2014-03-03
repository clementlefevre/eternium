package com.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Delivery_Notes;


public class Delivery_NotesDAO extends GenericDAO<Delivery_Notes> implements Serializable{

	private static final long serialVersionUID = 1L;

	public Delivery_NotesDAO() {
		super(Delivery_Notes.class);
	}


	public List<Delivery_Notes> findAll() {


		return super.findAll();

	}

	public void delete(Delivery_Notes delivery_notes) {
		super.delete(delivery_notes.getId(), Delivery_Notes.class);
	}



	public Delivery_Notes findDeliveryWithAllProducts(int id) {
		System.out.println("on tente le jointo sur DN et Products");
		System.out.println("id: "+id);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("delivery_notes_id", id);
		System.out.println("on tente le jointo sur DN et Products");
		return super.findOneResult(Delivery_Notes.FIND_DELIVERY_BY_ID_WITH_PRODUCTS, parameters);
	}



}