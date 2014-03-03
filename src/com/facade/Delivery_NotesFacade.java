package com.facade;

import java.io.Serializable;
import java.util.List;

import com.dao.Delivery_NotesDAO;
import com.model.Delivery_Notes;


public class Delivery_NotesFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Delivery_NotesDAO delivery_NotesDAO = new Delivery_NotesDAO();

	
	public List<Delivery_Notes> listAll() {
		
		delivery_NotesDAO.beginTransaction();
		List<Delivery_Notes> result = delivery_NotesDAO.findAll();
		delivery_NotesDAO.closeTransaction();
		return result;
	}
	
	public Delivery_Notes findDelivery_Notes(int id) {
		delivery_NotesDAO.beginTransaction();
		Delivery_Notes delivery_notes = delivery_NotesDAO.find(id);
		delivery_NotesDAO.closeTransaction();
		return delivery_notes;
	}
	
	public void updateDelivery_Notes(Delivery_Notes delivery_notes) {
		delivery_NotesDAO.beginTransaction();
	
		
		Delivery_Notes persistedDelivery_Notes = delivery_NotesDAO.find(delivery_notes.getId());
		
		
		persistedDelivery_Notes.setSupplier_list(delivery_notes.getSupplier_list());
		
		persistedDelivery_Notes.setDelivery_notes_name(delivery_notes.getDelivery_notes_name());
		persistedDelivery_Notes.setDelivery_date(delivery_notes.getDelivery_date());
		
		
		
		delivery_NotesDAO.commitAndCloseTransaction();
	
	}
	

	public void deleteDelivery_Notes(Delivery_Notes delivery_notes){
		delivery_NotesDAO.beginTransaction();
		Delivery_Notes persistedDelivery_Notes = delivery_NotesDAO.findReferenceOnly(delivery_notes.getId());
		delivery_NotesDAO.delete(persistedDelivery_Notes);
		delivery_NotesDAO.commitAndCloseTransaction();
		
	}

	public Delivery_Notes findDeliveryWithAllProducts(int id) {
		delivery_NotesDAO.beginTransaction();
		Delivery_Notes delivery_notes = delivery_NotesDAO.findDeliveryWithAllProducts(id);
		delivery_NotesDAO.closeTransaction();
		return delivery_notes;
	}
	
public void createDelivery_Notes(Delivery_Notes delivery_notes) {
		
	delivery_NotesDAO.beginTransaction();
	delivery_NotesDAO.save(delivery_notes);
	delivery_NotesDAO.commitAndCloseTransaction();
	}
	

}