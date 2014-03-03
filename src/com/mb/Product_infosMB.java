package com.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.SelectItem;

import com.facade.Item_familyFacade;
import com.facade.Product_infosFacade;
import com.facade.Supplier_listFacade;
import com.model.Item_family;
import com.model.Product_infos;
import com.model.Supplier_list;


@SessionScoped
@ManagedBean
public class Product_infosMB extends AbstractMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Product_infos product_infos;

	private List<Supplier_list> allSupplier_list;
	private List<Item_family> allItem_family;
	private Product_infosFacade product_infosFacade;
	private SelectItem[] supplier_listOptions;

	private Supplier_listFacade supplier_listFacade;
	private Item_familyFacade itemFamilyFacade;
	private HtmlDataTable table;
	private Product_infos product_infosForDetail;
	private List<Product_infos> allProduct_infosList;
	private List<Product_infos> filteredProduct_infos;
	
	public Product_infosFacade getProduct_infosFacade() {
		if (product_infosFacade == null) {
			product_infosFacade = new Product_infosFacade();
		}
		return product_infosFacade;
	}



	private void loadProduct_infos() {
		allProduct_infosList = getProduct_infosFacade().listAll();

	}
	public void resetProduct_infos() {
		product_infos = new Product_infos();
	}


	public List<Product_infos> getAllProduct_infos() {
		if (allProduct_infosList == null) {
			loadProduct_infos();
		}
		return allProduct_infosList;
	}

	public HtmlDataTable getTable() {
		return table;
	}

	public void setTable(HtmlDataTable table) {

		this.table = table;
	}



	public Product_infos getProduct_infos() {

		return product_infos;
	}

	public void setProduct_infos(Product_infos product_infos) {
		this.product_infos = product_infos;
	}



	public List<Product_infos> getFilteredProduct_infos() {
		return filteredProduct_infos;
	}



	public void setFilteredProduct_infos(List<Product_infos> filteredProduct_infos) {
		this.filteredProduct_infos = filteredProduct_infos;
	}

	public void updateProduct_infos() {
		try {

			getProduct_infosFacade().updateProduct_infos(product_infos);
			closeDialog();
			displayInfoMessageToUser("Product_infos Updated With Sucess");
			loadProduct_infos();
			resetProduct_infos();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public void deleteProduct_infos() {
		try {
			getProduct_infosFacade().deleteProduct_infos(product_infos);
			closeDialog();
			displayInfoMessageToUser("This Product_infos has bee deleted With Sucess");
			loadProduct_infos();
			resetProduct_infos();

		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not delete this Product_infos. Try again later");
			e.printStackTrace();
		}
	}



	public Date getToday() {

		return new Date();

	}


	public void setProduct_infosForDetail(Product_infos product_infos) {
		System.out.println("product_infos selected :");
		System.out.println(product_infos.getItem_infos_Id());
		product_infosForDetail = getProduct_infosFacade().findProduct_infos(product_infos.getItem_infos_Id());
	}

	public Product_infos getProduct_infosForDetail() {
		if (product_infosForDetail == null) {
			product_infosForDetail = new Product_infos();
			/*product_infosForDetail.setStatus(new ArrayList<Status>());*/
		}

		return product_infosForDetail;
	}
	public void resetProduct_infosWithProductsForDetail() {
		product_infosForDetail = new Product_infos();
	}

	public void createProduct_infos() {
		Boolean exists=false;
		
			
			for (Product_infos product_infos_check : allProduct_infosList){
				/*System.out.println("product_infos from allProduct_infoss List"
			+product_infos.getItem_number()+"-"
						+product_infos.getTrain_type().getTrain_family()+"--"+product_infoss.getTrain_type().getTrain_label());*/
				
				if (product_infos_check.getItem_number().equals(product_infos.getItem_number())
						&& product_infos_check.getItem_name().equals(product_infos.getItem_name())
						&& product_infos_check.getItem_german_name().equals(product_infos.getItem_german_name())
						
						){
					
						System.out.println("pb product_infos exists");
						exists=true;
						keepDialogOpen();
						displayErrorMessageToUser("Ops, this Product Info already exists.");
					}
				}
			
if (exists==false){
	try {
			getProduct_infosFacade().createProduct_infos(product_infos);

			closeDialog();
			displayInfoMessageToUser("New Product_infos Created With Success");
			loadProduct_infos();
			resetProduct_infos();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create this Product_infos. Try again later");
			e.printStackTrace();
		
		
	}
}
}

	
	public SelectItem[] getSupplier_listOptions() {
		getCompleteSupplier_list();
		
		supplier_listOptions = new SelectItem[allSupplier_list.size()+1];
		supplier_listOptions[0]=new SelectItem("");
		for(int i=0;i<allSupplier_list.size();i++){
			

			supplier_listOptions[i+1] = new SelectItem(allSupplier_list.get(i).getSupplier_name());
		}

		return supplier_listOptions;
	}
	public List<Supplier_list> getCompleteSupplier_list() {


		if (allSupplier_list == null) {

			supplier_listFacade = new Supplier_listFacade();
			allSupplier_list = supplier_listFacade.listAll();
		}
		return allSupplier_list;
	}

	public List<Item_family> getCompleteItem_family() {


		if (allItem_family == null) {

			itemFamilyFacade = new Item_familyFacade();
			allItem_family = itemFamilyFacade.listAll();
		}
		return allItem_family;
	}

	
	
}