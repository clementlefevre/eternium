package com.mb;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.SelectItem;

import com.facade.StatusFacade;
import com.facade.StatusNameFacade;
import com.model.Status;
import com.model.StatusName;


@SessionScoped
@ManagedBean
public class StatusMB extends AbstractMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Status> allStatusList;
	private List<StatusName> allStatusNameList;
	private Status status;
	private StatusFacade statusFacade;
	private StatusNameFacade viewAllStatusNameFacade;

	private HtmlDataTable table;
	private SelectItem[]statusOptions;

	/* load the All Status List ***********************************************/
	
	
	public StatusFacade getStatusFacade() {
		System.out.println("nouvelle StatusFacade");
		if (statusFacade == null) {
			statusFacade = new StatusFacade();
		}
		return statusFacade;
	}

	private void loadViewAllStatus() {
		allStatusList = getStatusFacade().listAll();
		Collections.sort(allStatusList ,Collections.reverseOrder());
	}

	public List<Status> getAllStatus() {
		if (allStatusList == null) {
			loadViewAllStatus();
		}
		return allStatusList;
	}
	/* load the All StatusNAME List ***********************************************/
	
	
	public StatusNameFacade getStatusNameFacade() {
		if (viewAllStatusNameFacade == null) {
			viewAllStatusNameFacade = new StatusNameFacade();
		}
		return viewAllStatusNameFacade;
	}

	private void loadViewAllStatusName() {
		allStatusNameList = getStatusNameFacade().listAll();
	}
	public List<StatusName> getAllStatusName() {
		if (allStatusNameList == null) {
			loadViewAllStatusName();
		}
		return allStatusNameList;
	}


	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public HtmlDataTable getTable() {
		return table;
	}

	public void setTable(HtmlDataTable table) {

		this.table = table;
	}

	public SelectItem[] getStatusOptions() {
		if (allStatusNameList == null) {
			loadViewAllStatusName();
		}
		
		statusOptions = new SelectItem[allStatusNameList.size()+1];
		statusOptions[0]=new SelectItem("");
		for(int i=0;i<allStatusNameList.size();i++){


			statusOptions[i+1] = new SelectItem(allStatusNameList.get(i).getStatus_name());
		}
		return statusOptions;
	}
	
	
	
	private void loadStatus() {
		allStatusList = getStatusFacade().listAll();
		
	}
	public void resetStatus() {
		status = new Status();
	}
	
	public void updateStatus() {
		try {
			System.out.println("updateStatus from StatusMB");
			getStatusFacade().updateStatus(status);
			closeDialog();
			displayInfoMessageToUser("Updated With Sucess");
			loadStatus();
			resetStatus();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

}