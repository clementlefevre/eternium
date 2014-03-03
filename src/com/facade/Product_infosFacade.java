package com.facade;

import java.io.Serializable;
import java.util.List;

import com.dao.Product_infosDAO;
import com.model.Product_infos;


public class Product_infosFacade implements Serializable{
	private static final long serialVersionUID = 1L;

	private Product_infosDAO product_infosDAO = new Product_infosDAO();


	public List<Product_infos> listAll() {
		System.out.println("test Product_infosFace list all");
		product_infosDAO.beginTransaction();
		List<Product_infos> result = product_infosDAO.findAll();
		product_infosDAO.closeTransaction();
		return result;
	}

	public Product_infos findProduct_infos(int item_infos_Id) {
		product_infosDAO.beginTransaction();
		Product_infos product_infos = product_infosDAO.find(item_infos_Id);
		product_infosDAO.closeTransaction();
		return product_infos;
	}

	public void deleteProduct_infos(Product_infos product_infos){
		product_infosDAO.beginTransaction();
		Product_infos persistedProduct_infos = product_infosDAO.findReferenceOnly(product_infos.getItem_infos_Id());
		product_infosDAO.delete(persistedProduct_infos);
		product_infosDAO.commitAndCloseTransaction();

	}

	public void updateProduct_infos(Product_infos product_infos) {
		product_infosDAO.beginTransaction();


		Product_infos persistedProduct_infos = product_infosDAO.find(product_infos.getItem_infos_Id());
		persistedProduct_infos.setItem_family(product_infos.getItem_family());

		/*persistedProduct_infos.setProduct_infos_serial_number(product_infos.getProduct_infos_serial_number());
		persistedProduct_infos.setStatus(product_infos.getStatus());
		persistedProduct_infos.setTrain_type(product_infos.getTrain_type());*/


		product_infosDAO.commitAndCloseTransaction();

	}

	public void createProduct_infos(Product_infos product_infos) {

		product_infosDAO.beginTransaction();
		product_infosDAO.save(product_infos);
		product_infosDAO.commitAndCloseTransaction();
	}

}