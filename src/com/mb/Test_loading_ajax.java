package com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;


@ManagedBean
@SessionScoped

public class Test_loading_ajax{

	
	private boolean loaded=false;
	
	public void onload(AjaxBehaviorEvent event) {
		  setLoaded(true);
		}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
	
	
	

	
}
