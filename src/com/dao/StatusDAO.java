package com.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Status;


public class StatusDAO extends GenericDAO<Status> implements Serializable{

	private static final long serialVersionUID = 1L;

	public StatusDAO() {
		super(Status.class);
	}

	public List<Status> findAllStatus() {
		return super.findAllMax();
	}
	public void delete(Status status) {
    	super.delete(status.getStatus_id(), Status.class);
}

	public Status findMaxStatusofOneProduct(int itemId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("item_id", itemId);

		return super.findOneResult(Status.FIND_MAX_STATUS_FOR_ONE_PRODUCT, parameters);
	}


}