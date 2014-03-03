package com.facade;

import java.io.Serializable;
import java.util.List;

import com.dao.ProductDAO;
import com.model.Product;


public class ProductFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ProductDAO productDAO = new ProductDAO();

	

	public List<Product> listAll() {
		productDAO.beginTransaction();
		List<Product> result = productDAO.findAll();
		productDAO.closeTransaction();
		return result;
	}

	public Product findProductWithAllStatus(int itemId) {
		productDAO.beginTransaction();
		Product product = productDAO.findProductWithAllStatus(itemId);
		productDAO.closeTransaction();
		return product;
	}
	
	public void createProduct(Product product) {
		
		productDAO.beginTransaction();
		productDAO.save(product);
		productDAO.commitAndCloseTransaction();
	}

	public void updateProduct(Product product) {
		productDAO.beginTransaction();
	
		
		Product persistedProduct = productDAO.find(product.getItem_id());
		
		
		//persistedProduct.setSerial(product.getSerial());
		
		persistedProduct.setProduct_infos(product.getProduct_infos());
		
		System.out.println("persistedProduct"+ persistedProduct.getProduct_infos().getItem_number()+"****");
		System.out.println("persistedProduct"+ persistedProduct.getProduct_infos().getItem_infos_Id()+"****");
		
		
		productDAO.commitAndCloseTransaction();
	
	}
	
	public void deleteProduct(Product product){
		productDAO.beginTransaction();
		Product persistedProductWithIdOnly = productDAO.findReferenceOnly(product.getItem_id());
		productDAO.delete(persistedProductWithIdOnly);
		productDAO.commitAndCloseTransaction();
		
	}

	public Product findProduct(int productId) {
		productDAO.beginTransaction();
		Product product = productDAO.find(productId);
		productDAO.closeTransaction();
		return product;
	}

}