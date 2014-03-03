package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.facade.Product_infosFacade;
import com.model.Product_infos;

@FacesConverter(forClass = com.model.Product_infos.class)
public class Product_infosConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Product_infosFacade product_infosFacade = new Product_infosFacade();
		int item_infos_Id;

		try {
			item_infos_Id = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Product Infos and select it (or use the dropdow)", "Type the name of a Product Infos and select it (or use the dropdow)"));
		}

		return product_infosFacade.findProduct_infos(item_infos_Id);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Product_infos product_infos = (Product_infos) arg2;
		return String.valueOf(product_infos.getItem_infos_Id());
	}
}
