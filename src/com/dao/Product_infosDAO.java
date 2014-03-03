package com.dao;

import java.io.Serializable;
import java.util.List;

import com.model.Product_infos;


public class Product_infosDAO extends GenericDAO<Product_infos> implements Serializable {

	private static final long serialVersionUID = 1L;

	public Product_infosDAO() {
		super(Product_infos.class);
	}


	public List<Product_infos> findAll() {

		System.out.println("test");

		return super.findAll();

	}

	public void delete(Product_infos product_infos) {
    	super.delete(product_infos.getItem_infos_Id(), Product_infos.class);
}



	


}