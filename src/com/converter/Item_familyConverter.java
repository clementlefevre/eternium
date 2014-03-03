package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.facade.Item_familyFacade;
import com.model.Item_family;

@FacesConverter(forClass = com.model.Item_family.class)
public class Item_familyConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Item_familyFacade item_familyFacade = new Item_familyFacade();
		int item_family_code;

		try {
			item_family_code = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Item Family and select it (or use the dropdow)", "Type the name of a Product Infos and select it (or use the dropdow)"));
		}

		return item_familyFacade.findItem_family(item_family_code);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Item_family item_family = (Item_family) arg2;
		return String.valueOf(item_family.getId());
	}
}
