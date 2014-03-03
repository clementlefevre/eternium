package com.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;

import com.facade.Delivery_NotesFacade;
import com.facade.Delivery_listFacade;
import com.facade.ProjectsFacade;
import com.facade.Supplier_listFacade;
import com.model.Delivery_Notes;
import com.model.Delivery_list;
import com.model.Product;
import com.model.Projects;
import com.model.Supplier_list;


@SessionScoped
@ManagedBean
public class DeliveryMB extends AbstractMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Supplier_list> allSupplier_list;
	private Delivery_Notes delivery_notes;
	private List<Delivery_Notes> filteredDelivery;  
	private Delivery_NotesFacade delivery_notesFacade;
	private ProjectsFacade projectsFacade;
	private Delivery_listFacade delivery_listFacade;
	private List<Delivery_Notes> allDeliveriesList;
	private List<Delivery_list> allDelivery_list;

	private List<Projects> allProjects;
	private HtmlDataTable table;
	private Delivery_Notes deliveryWithProductsForDetail;
	private Supplier_listFacade supplier_listFacade;

	public Delivery_NotesFacade getDelivery_NotesFacade() {
		if (delivery_notesFacade == null) {
			delivery_notesFacade = new Delivery_NotesFacade();
		}
		return delivery_notesFacade;
	}



	private void loadDeliveries() {
		allDeliveriesList = getDelivery_NotesFacade().listAll();

	}

	public void resetDelivery() {
		delivery_notes = new Delivery_Notes();
	}

	public List<Delivery_Notes> getAllDeliveries() {
		if (allDeliveriesList == null) {
			loadDeliveries();
		}
		return allDeliveriesList;
	}

	public HtmlDataTable getTable() {
		return table;
	}

	public void setTable(HtmlDataTable table) {

		this.table = table;
	}

	public List<Projects> getCompleteProjects() {


		if (allProjects == null) {

			projectsFacade = new ProjectsFacade();
			allProjects = projectsFacade.listAll();
		}
		return allProjects;
	}


	public Delivery_Notes getDelivery_notes() {

		return delivery_notes;
	}

	public void setDelivery_notes(Delivery_Notes delivery_notes) {
		this.delivery_notes = delivery_notes;
	}



	public List<Delivery_Notes> getFilteredDelivery() {
		return filteredDelivery;
	}



	public void setFilteredDelivery(List<Delivery_Notes> filteredDelivery) {
		this.filteredDelivery = filteredDelivery;
	}

	public void updateDelivery_Notes() {
		try {

			getDelivery_NotesFacade().updateDelivery_Notes(delivery_notes);
			closeDialog();
			displayInfoMessageToUser("Delivery Notes Updated With Sucess");
			loadDelivery_Notes();
			resetDelivery_Notes();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public void deleteDelivery_Notes() {
		try {
			getDelivery_NotesFacade().deleteDelivery_Notes(delivery_notes);
			closeDialog();
			displayInfoMessageToUser("This Delivery Notes has bee deleted With Sucess");
			loadDeliveries();
			resetDelivery_Notes();
			System.out.println("wesh c es tbon le delete");
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not delete this product. Try again later");
			e.printStackTrace();
		}
	}


	private void loadDelivery_Notes() {
		allDeliveriesList = getDelivery_NotesFacade().listAll();

	}

	public void resetDelivery_Notes() {
		delivery_notes = new Delivery_Notes();
	}

	public Date getToday() {

		return new Date();

	}

	public List<Supplier_list> getCompleteSupplier_list() {


		if (allSupplier_list == null) {

			supplier_listFacade = new Supplier_listFacade();
			allSupplier_list = supplier_listFacade.listAll();
		}
		return allSupplier_list;
	}


	public List<Delivery_list> getCompleteDelivery_list() {


		if (allDelivery_list == null) {

			delivery_listFacade = new Delivery_listFacade();
			allDelivery_list = delivery_listFacade.listAll();
		}
		return allDelivery_list;
	}



	public void setDeliveryWithProductsForDetail(Delivery_Notes delivery_notes) {
		System.out.println("delivery selected :");
		System.out.println(delivery_notes.getId());
		deliveryWithProductsForDetail = getDelivery_NotesFacade().findDeliveryWithAllProducts(delivery_notes.getId());
	}

	public Delivery_Notes getDeliveryWithProductsForDetail() {
		if (deliveryWithProductsForDetail == null) {
			deliveryWithProductsForDetail = new Delivery_Notes();
			deliveryWithProductsForDetail.setProduct(new ArrayList<Product>());
		}

		return deliveryWithProductsForDetail;
	}
	public void resetProductWithStatusForDetail() {
		deliveryWithProductsForDetail = new Delivery_Notes();
	}

	public void createDelivery_Notes() {
		try {

			getDelivery_NotesFacade().createDelivery_Notes(delivery_notes);

			closeDialog();
			displayInfoMessageToUser("New Delivery Created With Great Success");
			loadDelivery_Notes();
			resetDelivery_Notes();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create this Delivery. Try again later");
			e.printStackTrace();
		}
	}
}