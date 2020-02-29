package be.kuleuven.gent.project.jsf.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.faces.bean.*;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import be.kuleuven.gent.project.data.*;
import be.kuleuven.gent.project.ejb.*;
import be.kuleuven.gent.project.ejb.ProfielManagementEJBLocal;

@Named
@ViewScoped
public class ProfielController implements Serializable {
	private static final long serialVersionUID = 6731234724536164355L;
	
	@EJB
	private ProfielManagementEJBLocal profielEJB;

	@EJB
	private UserManagementEJBLocal userEJB;
	
	private Profiel profiel = new Profiel();
	
	public String createProfiel() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		profielEJB.createProfiel(profiel);
		return "index.faces?faces-redirect=true&login=1";
	}
	
	public String activateP() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		Profiel p = profielEJB.getProfiel();
		p.setGroup("Passenger");
		return "passenger/indexP.faces?faces-redirect=true&login=1";
		
	}
	
	public Profiel getProfiel() {
		
		return profiel;
	}
	public Profiel getHuidigProfiel() {
		Profiel p = profielEJB.getProfiel();
		return p;
	}

	public void setProfiel(Profiel profiel) {
		this.profiel = profiel;
	}
	
	
}
