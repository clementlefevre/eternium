package com.dao;

import java.io.Serializable;
import java.util.List;

import com.model.Supplier_list;


public class Supplier_listDAO extends GenericDAO<Supplier_list> implements Serializable{

	private static final long serialVersionUID = 1L;

	public Supplier_listDAO() {
		super(Supplier_list.class);
	}


	public List<Supplier_list> findAll() {

		
		return super.findAll();

	}

	public void delete(Supplier_list supplier_list) {
    	super.delete(supplier_list.getSupplier_code(), Supplier_list.class);
}



	


}