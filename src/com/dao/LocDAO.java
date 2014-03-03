package com.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Loc;


public class LocDAO extends GenericDAO<Loc> implements Serializable{

	private static final long serialVersionUID = 1L;

	public LocDAO() {
		super(Loc.class);
	}


	public List<Loc> findAllLoc() {

		

		return super.findAll();

	}

	public void delete(Loc loc) {
    	super.delete(loc.getLoc_id(), Loc.class);
}

	public Loc findLocWithAllProducts(int id) {
		System.out.println("on tente le jointo sur Loc et Status");
		System.out.println("loc_id: "+id);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("loc_id", id);
		System.out.println("on tente le jointo sur Loc et Status");
		return super.findOneResult(Loc.FIND_LOC_BY_ID_WITH_PRODUCTS, parameters);
	}

	


}