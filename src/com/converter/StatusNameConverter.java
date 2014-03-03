package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.facade.StatusNameFacade;
import com.model.StatusName;

@FacesConverter(forClass = com.model.StatusName.class)
public class StatusNameConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		StatusNameFacade status_nameFacade = new StatusNameFacade();
		int status_code;

		try {
			status_code = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Status Name and select it (or use the dropdow)", "Type the name of a Product Infos and select it (or use the dropdow)"));
		}

		return status_nameFacade.findStatusName(status_code);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		StatusName status_name = (StatusName) arg2;
		return String.valueOf(status_name.getStatus_code());
	}
}
