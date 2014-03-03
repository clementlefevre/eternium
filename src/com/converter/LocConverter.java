package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.facade.LocFacade;
import com.model.Loc;

@FacesConverter(forClass = com.model.Loc.class)
public class LocConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		LocFacade locFacade = new LocFacade();
		int loc_id;

		try {
			loc_id = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Loc Name and select it (or use the dropdow)", "Type the name of a Product Infos and select it (or use the dropdow)"));
		}

		return locFacade.findLoc(loc_id);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Loc loc = (Loc) arg2;
		return String.valueOf(loc.getLoc_id());
	}
}
