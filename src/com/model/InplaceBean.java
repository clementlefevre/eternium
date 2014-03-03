package com.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
	public class InplaceBean implements Serializable{  
		  
	    /**
	 * 
	 */
	private static final long serialVersionUID = -3364130772138746356L;
		private String text = "PrimeFaces";  
	  
	    public String getText() {  
	        return text;  
	    }  
	  
	    public void setText(String text) {  
	        this.text = text;  
	    }  
	}  

