package com.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MenuMB  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int selectedOption;

	public MenuMB() {
		selectedOption = 0;
	}

	public int getSelectedOption() {
		return selectedOption;
	}

	public String setSelectedOption(int selectedOption) {

		this.selectedOption = selectedOption;
		System.out.println("option"+selectedOption);
		if (selectedOption==0){
			return ("/pages/protected/index.xhtml");
			}
		if (selectedOption==1){
		return ("/pages/protected/defaultUser/products.xhtml");
		}
		if (selectedOption==2){
			return ("/pages/protected/defaultUser/loc.xhtml");
			}
		if (selectedOption==3){
			return ("/pages/protected/defaultUser/delivery.xhtml");
			}
		if (selectedOption==4){
			return ("/pages/protected/defaultUser/product_infos.xhtml");
			}
		
		return null;
		
	}

	
}