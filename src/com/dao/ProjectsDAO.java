package com.dao;

import java.io.Serializable;
import java.util.List;

import com.model.Projects;


public class ProjectsDAO extends GenericDAO<Projects> implements Serializable {

	private static final long serialVersionUID = 1L;

	public ProjectsDAO() {
		super(Projects.class);
	}


	public List<Projects> findAll() {

		
		return super.findAll();

	}

	public void delete(Projects project) {
    	super.delete(project.getProject_id(), Projects.class);
}



	


}