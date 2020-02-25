package be.kuleuven.gent.project.jsf.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import be.kuleuven.gent.project.data.*;
import be.kuleuven.gent.project.ejb.*;
import be.kuleuven.gent.project.ejb.ProfielManagementEJBLocal;

@Named
@Stateless
public class ProfielController implements Serializable {
	private static final long serialVersionUID = 6731234724536164355L;
	
	@EJB
	private ProfielManagementEJBLocal profielEJB;

	@EJB
	private UserManagementEJBLocal userEJB;
	
	private Profiel profiel = new Profiel();
	
	public String createProfiel() {
		profielEJB.createProfiel(profiel);
		return "test.faces?faces-redirect=true";
	}

	public Profiel getProfiel() {
		return profiel;
	}

	public void setProfiel(Profiel profiel) {
		this.profiel = profiel;
	}
	
	
}
