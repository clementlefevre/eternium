package com.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
	public class InplaceBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;  
	
		private String text = "PrimeFaces";  
	  
	    public String getText() {  
	        return text;  
	    }  
	  
	    public void setText(String text) {  
	        this.text = text;  
	    }  
	}  

