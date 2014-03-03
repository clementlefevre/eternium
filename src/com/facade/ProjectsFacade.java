package com.facade;

import java.io.Serializable;
import java.util.List;

import com.dao.ProjectsDAO;
import com.model.Projects;


public class ProjectsFacade implements Serializable{
	private static final long serialVersionUID = 1L;

	private ProjectsDAO projectDAO = new ProjectsDAO();


	public List<Projects> listAll() {

		projectDAO.beginTransaction();
		List<Projects> result = projectDAO.findAll();
		projectDAO.closeTransaction();
		return result;
	}

	public Projects findProjects(int id) {
		projectDAO.beginTransaction();
		Projects delivery_notes = projectDAO.find(id);
		projectDAO.closeTransaction();
		return delivery_notes;
	}

	public void updateProjects(Projects project) {
		projectDAO.beginTransaction();


	//	Projects persistedProjects = projectDAO.find(project.getProject_id());

		/*
		persistedProjects.setSupplier_list(project.getSupplier_list());

		persistedProjects.setDelivery_notes_name(project.getDelivery_notes_name());
		persistedProjects.setDelivery_date(project.getDelivery_date());*/



		projectDAO.commitAndCloseTransaction();

	}


	public void deleteProjects(Projects project){
		projectDAO.beginTransaction();
		Projects persistedProjects = projectDAO.findReferenceOnly(project.getProject_id());
		projectDAO.delete(persistedProjects);
		projectDAO.commitAndCloseTransaction();

	}



	public void createProjects(Projects project) {

		projectDAO.beginTransaction();
		projectDAO.save(project);
		projectDAO.commitAndCloseTransaction();
	}


}