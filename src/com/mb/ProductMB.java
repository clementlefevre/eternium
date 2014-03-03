package com.mb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;

import com.facade.Delivery_NotesFacade;
import com.facade.Item_familyFacade;
import com.facade.LocFacade;
import com.facade.ProductFacade;
import com.facade.Product_infosFacade;
import com.facade.StatusFacade;
import com.facade.StatusNameFacade;
import com.facade.Train_typeFacade;
import com.model.Delivery_Notes;
import com.model.Item_family;
import com.model.LazyStatusDataModel;
import com.model.Loc;
import com.model.Product;
import com.model.ProductDataModel;
import com.model.Product_infos;
import com.model.Status;
import com.model.StatusName;
import com.model.Train_type;




@SessionScoped
@ManagedBean
public class ProductMB extends AbstractMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Status> allProductsList;
	private List<Product> importedProductsList;
	private List<Product_infos> allProduct_infos;
	private List<Loc> allLoc;
	private List<Delivery_Notes> allDelivery_Notes;
	private List<StatusName> allStatusName;
	private List<Item_family> allItem_family;
	private List<Status> allStatusList;
	private List<Train_type> allTrain_family;
	private List<Train_type>  allTrain_label;
	private Product product;
	private Product_infos product_infos;
	private Status status;
	private Delivery_Notes delivery_notes;
	private Product_infosFacade product_infosFacade;
	private LocFacade locFacade;
	private Delivery_NotesFacade delivery_notesFacade;
	private StatusFacade statusFacade;
	private StatusNameFacade statusNameFacade;
	private Item_familyFacade item_familyFacade;
	private ProductFacade productFacade;
	private Train_typeFacade train_typeFacade;
	private HtmlDataTable table;
	private Product productWithStatusForDetail;
	private Status lastStatusOfProduct;
	private Status lastStatusOfAllProducts;
	private SelectItem[]statusOptions;
	private SelectItem[]item_familyOptions;

	private SelectItem[] train_familyOptions;
	private SelectItem[] train_labelOptions;
	private int status_name_selected;
	private List<Status> filteredStatus;

	private LazyDataModel<Status> lazyModel;
	private String destination="\\resources\\";

	private Product[] selectedImportedProducts;
	private ProductDataModel mediumProductsModel;


	public ProductMB(){
		loadProducts();
		lazyModel = new LazyStatusDataModel(getAllProducts()); 
		importedProductsList =  new ArrayList<Product>();

	}

	public LazyDataModel<Status> getLazyModel() {  
		return lazyModel;  
	} 
	public List<Product> getimportedProductList() {

		

		return importedProductsList;  

	} 

	public void createProduct() {
		try {

			getProductFacade().createProduct(product);
			status=new Status();
			status.setStatus_name(getStatusNameFacade().findStatusName(1));
			System.out.println(status.getStatus_name().getStatus_name());
			status.setProduct(product);
			getStatusFacade().createStatus(status);
			closeDialog();
			displayInfoMessageToUser("Product Created With Success");
			loadProducts();
			lazyModel = new LazyStatusDataModel(getAllProducts()); 
			resetProduct();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create this Product. Try again later");
			e.printStackTrace();
		}
	}



	public List<Product_infos> completeProduct_infos(String name) {
		List<Product_infos> queryResult = new ArrayList<Product_infos>();

		if (allProduct_infos == null) {

			product_infosFacade = new Product_infosFacade();
			allProduct_infos = product_infosFacade.listAll();
		}


		for (Product_infos product_infos : allProduct_infos) {
			if (product_infos.getItem_number().toLowerCase().contains(name.toLowerCase())) {
				queryResult.add(product_infos);
			}
		}



		return queryResult;
	}

	public List<Loc> completeLoc(String name) {

		List<Loc> queryResult = new ArrayList<Loc>();

		if (allLoc == null) {

			locFacade = new LocFacade();
			allLoc = locFacade.listAll();
		}


		for (Loc loc : allLoc) {
			String autocomplete_input =loc.getLoc_serial_number()+loc.getTrain_type().getTrain_family()+loc.getTrain_type().getTrain_label();

			if (autocomplete_input.toLowerCase().contains(name.toLowerCase())) {
				queryResult.add(loc);
			}

		}



		return queryResult;
	}


	public List<Delivery_Notes> completeDelivery_Notes(String name) {
		List<Delivery_Notes> queryResult = new ArrayList<Delivery_Notes>();

		if (allDelivery_Notes == null) {

			delivery_notesFacade = new Delivery_NotesFacade();
			allDelivery_Notes = delivery_notesFacade.listAll();
		}



		for (Delivery_Notes delivery_notes : allDelivery_Notes) {
			if (delivery_notes.getDelivery_notes_name().toLowerCase().contains(name.toLowerCase())) {
				queryResult.add(delivery_notes);
			}
		}



		return queryResult;
	}

	public List<StatusName> getCompleteStatusName() {


		if (allStatusName == null) {

			statusNameFacade = new StatusNameFacade();
			allStatusName = statusNameFacade.listAll();
		}
		return allStatusName;
	}

	public List<Item_family> getCompleteItem_family() {


		if (allItem_family == null) {

			item_familyFacade = new Item_familyFacade();
			allItem_family = item_familyFacade.listAll();
		}
		return allItem_family;
	}

	public List<Loc> getCompleteLoc() {


		if (allLoc == null) {

			locFacade = new LocFacade();
			allLoc = locFacade.listAll();
		}
		return allLoc;
	}

	public List<Train_type> getCompleteTrain_family() {


		if (allTrain_family == null) {

			train_typeFacade = new Train_typeFacade();
			allTrain_family = train_typeFacade.listAllTrain_type_family();
		}
		System.out.println("allTrain_family" +allTrain_family.size());
		return allTrain_family;
	}

	public List<Train_type> getCompleteTrain_label() {


		if (allTrain_label == null) {

			train_typeFacade = new Train_typeFacade();
			allTrain_label = train_typeFacade.listAllTrain_type_label();
		}
		System.out.println("allTrain_family" +allTrain_label.size());
		return allTrain_label;
	}





	public ProductFacade getProductFacade() {
		if (productFacade == null) {
			productFacade = new ProductFacade();
		}
		return productFacade;
	}

	public StatusFacade getStatusFacade() {

		if (statusFacade == null) {

			statusFacade = new StatusFacade();

		}

		return statusFacade;
	}

	public StatusNameFacade getStatusNameFacade() {
		if (statusNameFacade == null) {
			statusNameFacade = new StatusNameFacade();
		}
		return statusNameFacade;
	}

	private void loadProducts() {
		allProductsList = getStatusFacade().listAll();
		Collections.sort(allProductsList ,Collections.reverseOrder());
	}

	public void resetProduct() {
		product = new Product();
	}

	public List<Status> getAllProducts() {
		/*if (allProductsList == null) {*/
		loadProducts();
		/*}*/
		return allProductsList;
	}
	public Product getProduct() {


		return product;
	}
	public void setProduct(Product product) {
		this.product = product;


	}
	public HtmlDataTable getTable() {
		return table;
	}

	public void setTable(HtmlDataTable table) {

		this.table = table;
	}

	public void setProductWithStatusForDetail(Product product) {

		productWithStatusForDetail = getProductFacade().findProductWithAllStatus(product.getItem_id());

	}

	public Product getProductWithStatusForDetail() {
		if (productWithStatusForDetail == null) {
			productWithStatusForDetail = new Product();
			productWithStatusForDetail.setStatus(new ArrayList<Status>());
		}

		return productWithStatusForDetail;
	}
	public void resetProductWithStatusForDetail() {
		productWithStatusForDetail = new Product();
	}

	public void updateProduct() {
		try {

			getProductFacade().updateProduct(product);
			closeDialog();
			displayInfoMessageToUser("Updated With Sucess");
			loadProducts();
			resetProduct();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public void deleteProduct() {
		try {
			getProductFacade().deleteProduct(product);
			closeDialog();
			displayInfoMessageToUser("This Product has been deleted With Sucess");
			loadProducts();

			lazyModel = new LazyStatusDataModel(getAllProducts()); 
			resetProduct();

		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not delete this product. Try again later");
			e.printStackTrace();
		}
	}

	public Product_infos getProduct_infos() {
		if (product_infos == null) {
			product_infos = new Product_infos();
		}return product_infos;
	}

	public void setProduct_infos(Product_infos product_infos) {
		this.product_infos = product_infos;
	}

	public Delivery_Notes getDelivery_notes() {
		if (delivery_notes == null) {
			delivery_notes = new Delivery_Notes();
		}return delivery_notes;
	}

	public void setDelivery_notes(Delivery_Notes delivery_notes) {
		this.delivery_notes = delivery_notes;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	public void updateStatus() {
		try {

			getStatusFacade().updateStatus(status);
			closeDialog();
			displayInfoMessageToUser("Status Updated With Sucess");
			loadStatus();
			resetStatus();
			loadProducts();
			resetProduct();

		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not update. Try again later");
			e.printStackTrace();
		}
	}

	public void createStatus() {
		try {
			status.setProduct(productWithStatusForDetail);
			getStatusFacade().createStatus(status);
			closeDialog();
			displayInfoMessageToUser("New Status Created With Success");
			setProductWithStatusForDetail(productWithStatusForDetail);
			loadStatus();
			resetStatus();
			loadProducts();
			resetProduct();

		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create this Status. Try again later");
			e.printStackTrace();
		}
	}

	public void createStatusCode(){
		status_name_selected=status.getStatus_name().getStatus_code();
		System.out.println("statusnameselected :"+status_name_selected);

	}


	public void deleteStatus() {
		try {
			getStatusFacade().deleteStatus(status);
			closeDialog();
			displayInfoMessageToUser("This Status has bee deleted With Sucess");
			setProductWithStatusForDetail(productWithStatusForDetail);
			loadStatus();
			resetStatus();
			loadProducts();
			resetProduct();

			System.out.println("wesh c es tbon le delete");
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not delete this status. Try again later");
			e.printStackTrace();
		}
	}

	private void loadStatus() {
		allStatusList = getStatusFacade().listAll();

	}

	public void resetStatus() {
		status = new Status();
	}



	public Status getLastStatusOfProduct() {
		if (productWithStatusForDetail.getItem_id() != null) {
			lastStatusOfProduct = new Status();

			System.out.println("getLastStatusOfProduct");
			System.out.println(productWithStatusForDetail.getItem_id());
			lastStatusOfProduct = getStatusFacade().findMaxStatusofOneProduct(productWithStatusForDetail.getItem_id());
			return lastStatusOfProduct;
		}
		return null;
	}



	public void setLastStatusOfProduct(Status lastStatusOfProduct) {
		lastStatusOfProduct = getStatusFacade().findMaxStatusofOneProduct(productWithStatusForDetail.getItem_id());
		this.lastStatusOfProduct = lastStatusOfProduct;
	}

	public Status getLastStatusOfAllProducts(Product product) {
		if (product.getItem_id() != null) {
			lastStatusOfAllProducts = new Status();
			System.out.println("lastStatus of Product Id :");
			System.out.println(product.getItem_id());

			lastStatusOfAllProducts = getStatusFacade().findMaxStatusofOneProduct(product.getItem_id());
			return lastStatusOfAllProducts;
		}
		return null;
	}

	public void setLastStatusOfAllProducts(Status lastStatusOfAllProducts) {
		lastStatusOfAllProducts = getStatusFacade().findMaxStatusofOneProduct(product.getItem_id());
		this.lastStatusOfAllProducts = lastStatusOfAllProducts;
	}

	public SelectItem[] getStatusOptions() {
		getCompleteStatusName();

		statusOptions = new SelectItem[allStatusName.size()+1];
		statusOptions[0]=new SelectItem("");
		for(int i=0;i<allStatusName.size();i++){


			statusOptions[i+1] = new SelectItem(allStatusName.get(i).getStatus_name());
		}

		return statusOptions;
	}

	public SelectItem[] getItem_familyOptions() {
		getCompleteItem_family();

		item_familyOptions = new SelectItem[allItem_family.size()+1];
		item_familyOptions[0]=new SelectItem("");
		for(int i=0;i<allItem_family.size();i++){


			item_familyOptions[i+1] = new SelectItem(allItem_family.get(i).getFamily_name());
		}

		return item_familyOptions;
	}

	/*public SelectItem[] getLocOptions() {
		getCompleteLoc();

		locOptions = new SelectItem[allLoc.size()+1];
		locOptions[0]=new SelectItem("");
		for(int i=0;i<allLoc.size();i++){


			locOptions[i+1] = new SelectItem(allLoc.get(i).getTrain_type().getTrain_family());
		}

		return locOptions;
	}*/

	public SelectItem[] getTrain_familyOptions() {
		getCompleteTrain_family();

		train_familyOptions = new SelectItem[allTrain_family.size()+1];
		train_familyOptions[0]=new SelectItem("");
		for(int i=0;i<allTrain_family.size();i++){


			train_familyOptions[i+1] = new SelectItem(allTrain_family.get(i).getTrain_family());
		}

		return train_familyOptions;
	}

	public SelectItem[] getTrain_labelOptions() {
		getCompleteTrain_label();

		train_labelOptions = new SelectItem[allTrain_label.size()+1];
		train_labelOptions[0]=new SelectItem("");
		for(int i=0;i<allTrain_label.size();i++){


			train_labelOptions[i+1] = new SelectItem(allTrain_label.get(i).getTrain_label());
		}

		return train_labelOptions;
	}



	public int getStatus_name_selected() {
		return status_name_selected;
	}



	public void setStatus_name_selected(int status_name_selected) {
		this.status_name_selected = status_name_selected;
	}

	public List<Status> getFilteredStatus() {
		return filteredStatus;
	}

	public void setFilteredStatus(List<Status> filteredStatus) {
		this.filteredStatus = filteredStatus;
	}

	public void upload(FileUploadEvent event) { 
		importedProductsList =  new ArrayList<Product>();
		mediumProductsModel = new ProductDataModel();
		/*FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
		FacesContext.getCurrentInstance().addMessage(null, msg);*/
		// Do what you want with the file        
		try {
			copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(event.getFile().getFileName());

		readFile(event.getFile().getFileName());


	}  

	public void copyFile(String fileName, InputStream in) {

		try {


			// write the inputStream to a FileOutputStream
			File copyFile = new File(fileName);
			System.out.println("Location: copyFile" + copyFile.getAbsolutePath());
			OutputStream out = new FileOutputStream(copyFile);

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

			System.out.println("New file created!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void readFile(String fileName) {
		int i = 1000000;
		System.out.println("readFilde"+ fileName);

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";




		try {
			// Read the .csv File
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				String product_number ="";
				String product_serial="";
				String product_delivery_notes="";
				String product_manufacturing_date="";
				String product_qty_delivered="";
				String product_revision_code="";
				Product importedProduct=null;
				Boolean product_number_exists=false;
				Boolean product_delivery_notes_exists=false;
				Boolean product_exists=false;

				String[] product_csv = line.split(cvsSplitBy);
				product_number=String.valueOf(product_csv[0]);
				product_delivery_notes=String.valueOf(product_csv[1]);
				product_serial=String.valueOf(product_csv[2]);
				product_manufacturing_date=String.valueOf(product_csv[3]);
				product_qty_delivered=String.valueOf(product_csv[4]);
				product_revision_code=String.valueOf(product_csv[5]);
				System.out.println("product_number ["+product_number+"] serial ["+product_serial +" ]");



				if (allProduct_infos == null) {

					product_infosFacade = new Product_infosFacade();
					allProduct_infos = product_infosFacade.listAll();
				}

				// Check if Product Number does exist
				for (Product_infos product_info : allProduct_infos){



					if(product_number.equals(product_info.getItem_number()))
					{
						importedProduct= new Product();
						importedProduct.setProduct_infos(product_info);
						product_number_exists=true;		
						System.out.println(importedProduct.getProduct_infos().getItem_infos_Id());

					}
				}
				if(product_number_exists){
					if (allDelivery_Notes == null) {
						delivery_notesFacade = new Delivery_NotesFacade();
						allDelivery_Notes = delivery_notesFacade.listAll();
					}
					//Check if Delivery Notes exists
					for (Delivery_Notes delivery_notes : allDelivery_Notes)
					{
						if(product_delivery_notes.equals(delivery_notes.getDelivery_notes_name()))
						{
							System.out.println("le product_number existe dans la liste des DN");
							product_delivery_notes_exists=true;
							importedProduct.setDelivery_notes(delivery_notes);
						}

					}}
				if (product_number_exists && product_delivery_notes_exists)
				{
					for (Status status : allProductsList){
						if (product_number.equals(status.getProduct().getProduct_infos().getItem_number()) 
								&& product_serial.equals(status.getProduct().getSerial())//
								){
							displayErrorMessageToUser("This Product already exists  !!");
							displayErrorMessageToUser(product_number);
							displayErrorMessageToUser(product_serial);
							product_exists=true;

						}}
					if (!product_exists){

						importedProduct.setSerial(product_serial);
						importedProduct.setManufacturing_Date(product_manufacturing_date);
						importedProduct.setQuantity_delivered(Integer.parseInt(product_qty_delivered));
						importedProduct.setItem_id(i);
						importedProductsList.add(importedProduct);
						i++;
					}}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		mediumProductsModel = new ProductDataModel(importedProductsList);
	}

	public void importProducts() {
		System.out.println("on tente l import");
		System.out.println("selectedImportedProducts.length" +getSelectedImportedProducts().length);
		for (Product importProduct : getSelectedImportedProducts()){

			try{
				importProduct.setItem_id(null);
				getProductFacade().createProduct(importProduct);
				status=new Status();
				status.setStatus_name(getStatusNameFacade().findStatusName(1));
				System.out.println("status.setStatus_name(getStatusNameFacade().findStatusName(1)); OK");
				System.out.println(status.getStatus_name().getStatus_name());
				status.setProduct(importProduct);
				getStatusFacade().createStatus(status);
				closeDialog();
				displayInfoMessageToUser("This Product has been imported With Sucess");
				loadProducts();
				lazyModel = new LazyStatusDataModel(getAllProducts()); 
				selectedImportedProducts= null;
				mediumProductsModel = new ProductDataModel();
			}catch (Exception e) {
				keepDialogOpen();
				displayErrorMessageToUser("Ops, we could not import those Products. Try again later");
				e.printStackTrace();
			}
		}
	}
	public Product[] getSelectedImportedProducts() {
		System.out.println("getSelectedImportedProducts");
		return selectedImportedProducts;
	}

	public void setSelectedImportedProducts(Product[] selectedImportedProducts) {
		System.out.println("on set le setSelectedImportedProducts");

		this.selectedImportedProducts = selectedImportedProducts;
		System.out.println("length : "+selectedImportedProducts.length);
	}

	public ProductDataModel getMediumProductsModel() {
		return mediumProductsModel;
	}
	public void check(){

	}}