package com.facade;

import java.io.Serializable;
import java.util.List;

import com.dao.Item_familyDAO;
import com.model.Item_family;


public class Item_familyFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Item_familyDAO item_familyDAO = new Item_familyDAO();

	
	public List<Item_family> listAll() {
	
		item_familyDAO.beginTransaction();
		List<Item_family> result = item_familyDAO.findAll();
		item_familyDAO.closeTransaction();
		return result;
	}
	public Item_family findItem_family(int id) {
		item_familyDAO.beginTransaction();
		Item_family item_family = item_familyDAO.find(id);
		item_familyDAO.closeTransaction();
		return item_family;
	}

}