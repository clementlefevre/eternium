package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.facade.Delivery_NotesFacade;
import com.model.Delivery_Notes;

@FacesConverter(forClass = com.model.Delivery_Notes.class)
public class Delivery_NotesConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Delivery_NotesFacade delivery_notesFacade = new Delivery_NotesFacade();
		int id;

		try {
			id = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Product Infos and select it (or use the dropdow)", "Type the name of a Product Infos and select it (or use the dropdow)"));
		}

		return delivery_notesFacade.findDelivery_Notes(id);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Delivery_Notes delivery_notes = (Delivery_Notes) arg2;
		return String.valueOf(delivery_notes.getId());
	}
}
