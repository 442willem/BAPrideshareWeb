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
public class BerichtController implements Serializable {
	private static final long serialVersionUID = 6731274724536164355L;
	
	@EJB
	private ProfielManagementEJBLocal profielEJB;

	@EJB
	private UserManagementEJBLocal userEJB;
	
	private Profiel zender = new Profiel();
	private Profiel ontvanger = new Profiel();
	
	public Profiel getZender() {
		return zender;
	}
	public void setZender(Profiel zender) {
		this.zender = zender;
	}
	public Profiel getOntvanger() {
		return ontvanger;
	}
	public void setOntvanger(Profiel ontvanger) {
		this.ontvanger = ontvanger;
	}	
	
}
