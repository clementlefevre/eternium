package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.facade.Supplier_listFacade;
import com.model.Supplier_list;

@FacesConverter(forClass = com.model.Supplier_list.class)
public class Supplier_listConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Supplier_listFacade supplier_listFacade = new Supplier_listFacade();
		int id;

		try {
			id = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Product Infos and select it (or use the dropdow)", "Type the name of a Product Infos and select it (or use the dropdow)"));
		}

		return supplier_listFacade.findSupplier_list(id);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Supplier_list supplier_list = (Supplier_list) arg2;
		return String.valueOf(supplier_list.getSupplier_code());
	}
}
