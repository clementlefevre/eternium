package com.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Product;



public class ProductDAO extends GenericDAO<Product> implements Serializable{

	private static final long serialVersionUID = 1L;

	public ProductDAO() {
		super(Product.class);
	}



	public List<Product> findAllProduct() {

		

		return super.findAll();

	}
	
	public Product findProductWithAllStatus(int itemId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("item_id", itemId);

		return super.findOneResult(Product.FIND_PRODUCT_BY_ID_WITH_STATUS, parameters);
	}

	public void delete(Product product) {
        	super.delete(product.getItem_id(), Product.class);
	}
}





