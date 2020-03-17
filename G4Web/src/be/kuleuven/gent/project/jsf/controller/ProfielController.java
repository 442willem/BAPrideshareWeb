package be.kuleuven.gent.project.jsf.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.faces.bean.*;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
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
		return "index.faces?faces-redirect=true";
	}
	
	public Profiel getProfiel() {
		
		return profiel;
	}
	public Profiel getHuidigProfiel() {
		Profiel p = profielEJB.getProfiel();
		return p;
	}
	public Profiel findProfiel() {
		return profielEJB.findProfiel(profiel.getId());
	}

	public void setProfiel(Profiel profiel) {
		this.profiel = profiel;
	}
	public void logout() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.invalidateSession();
	    try {
			ec.redirect(ec.getRequestContextPath() + "/index.faces");
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
	
	
	
}
