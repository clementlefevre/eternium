package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.facade.LocFacade;
import com.facade.Train_typeFacade;
import com.model.Loc;
import com.model.Train_type;

@FacesConverter(forClass = com.model.Train_type.class)
public class Train_typeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Train_typeFacade train_typeFacade = new Train_typeFacade();
		int id;

		try {
			id = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Loc Name and select it (or use the dropdow)", "Type the name of a Product Infos and select it (or use the dropdow)"));
		}

		return train_typeFacade.findTrain_type(id);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Train_type train_type = (Train_type) arg2;
		return String.valueOf(train_type.getTrain_type_id());
	}
}
