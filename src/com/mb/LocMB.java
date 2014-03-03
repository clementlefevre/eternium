package com.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.SelectItem;

import com.facade.LocFacade;
import com.facade.Train_typeFacade;
import com.model.Loc;
import com.model.Status;
import com.model.Train_type;


@SessionScoped
@ManagedBean
public class LocMB extends AbstractMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Loc loc;



	private LocFacade locFacade;


	private SelectItem[] train_familyOptions;
	private SelectItem[] train_labelOptions;
	private List<Train_type> allTrain_family;
	private List<Train_type>  allTrain_label;
	private HtmlDataTable table;
	private Loc locWithProductsForDetail;
	private List<Loc> allLocsList;
	private List<Loc> filteredLoc;
	private List<Train_type> allTrain_type;
	private Train_typeFacade train_typeFacade;
	public LocFacade getLocFacade() {
		if (locFacade == null) {
			locFacade = new LocFacade();
		}
		return locFacade;
	}



	private void loadLocs() {
		allLocsList = getLocFacade().listAll();

	}
	public void resetLoc() {
		loc = new Loc();
	}


	public List<Loc> getAllLocs() {
		if (allLocsList == null) {
			loadLocs();
		}
		return allLocsList;
	}

	public HtmlDataTable getTable() {
		return table;
	}

	public void setTable(HtmlDataTable table) {

		this.table = table;
	}



	public Loc getLoc() {

		return loc;
	}

	public void setLoc(Loc loc) {
		this.loc = loc;
	}



	public List<Loc> getFilteredLoc() {
		return filteredLoc;
	}



	public void setFilteredLoc(List<Loc> filteredLoc) {
		this.filteredLoc = filteredLoc;
	}

	public void updateLoc() {
		try {

			getLocFacade().updateLoc(loc);
			closeDialog();
			displayInfoMessageToUser("Loc Updated With Sucess");
			loadLocs();
			resetLoc();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public void deleteLoc() {
		try {
			getLocFacade().deleteLoc(loc);
			closeDialog();
			displayInfoMessageToUser("This Loc has bee deleted With Sucess");
			loadLocs();
			resetLoc();

		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not delete this Loc. Try again later");
			e.printStackTrace();
		}
	}



	public Date getToday() {

		return new Date();

	}


	public void setLocWithProductsForDetail(Loc loc) {
		System.out.println("loc selected :");
		System.out.println(loc.getLoc_id());
		locWithProductsForDetail = getLocFacade().findLocWithAllProducts(loc.getLoc_id());
	}

	public Loc getLocWithProductsForDetail() {
		if (locWithProductsForDetail == null) {
			locWithProductsForDetail = new Loc();
			locWithProductsForDetail.setStatus(new ArrayList<Status>());
		}

		return locWithProductsForDetail;
	}
	public void resetLocWithProductsForDetail() {
		locWithProductsForDetail = new Loc();
	}

	public void createLoc() {
		Boolean exists=false;
		
			System.out.println(loc.getLoc_serial_number()+loc.getTrain_type().getTrain_family());
			for (Loc locs : allLocsList){
				System.out.println("locs from allLocs List"+locs.getLoc_serial_number()+"-"+locs.getTrain_type().getTrain_family()+"--"+locs.getTrain_type().getTrain_label());
				
				if (locs.getLoc_serial_number().equals(loc.getLoc_serial_number())
						&& locs.getTrain_type().getTrain_family().equals(loc.getTrain_type().getTrain_family())
						&& locs.getTrain_type().getTrain_label().equals(loc.getTrain_type().getTrain_label())
						
						){
					
						System.out.println("pb loc exists"+locs.getLoc_serial_number()+"-"+locs.getTrain_type().getTrain_family()+"--"+locs.getTrain_type().getTrain_label());
						exists=true;
						keepDialogOpen();
						displayErrorMessageToUser("Ops, this Loc already exists.");
					}
				}
			
if (exists==false){
	try {
			getLocFacade().createLoc(loc);

			closeDialog();
			displayInfoMessageToUser("New Loc Created With Success");
			loadLocs();
			resetLoc();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create this Loc. Try again later");
			e.printStackTrace();
		
		
	}
}
}

	public List<Train_type> getCompleteTrain_type() {


		if (allTrain_type == null) {

			train_typeFacade = new Train_typeFacade();
			allTrain_type = train_typeFacade.listAll();
		}
		return allTrain_type;
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
}