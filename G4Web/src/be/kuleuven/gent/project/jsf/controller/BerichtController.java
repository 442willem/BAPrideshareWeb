package be.kuleuven.gent.project.jsf.controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
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
	
	@EJB
	private BerichtManagementEJBLocal berichtEJB;
	
	private Bericht bericht;
	
	@PostConstruct
	public void init() {
		bericht = new Bericht();
		bericht.setOntvanger(new Profiel());
		bericht.setZender(profielEJB.getProfiel());
	}
	
	public Profiel getOntvanger() {
		return bericht.getOntvanger();
	}
	public void setOntvanger(Profiel ontvanger) {
		this.bericht.setOntvanger(ontvanger);
	}	
	public Bericht getBericht() {
		return bericht;
	}
	public void setBericht(Bericht bericht) {
		this.bericht = bericht;
	}
	public List<Bericht> getConversation(){
		return berichtEJB.getConversation(profielEJB.getProfiel().getId(), bericht.getOntvanger().getId());
	}
	public String sendBericht() {
		Date date = new Date();
		bericht.setTimestamp(new Timestamp(date.getTime()));
		bericht.setZender(profielEJB.getProfiel());
		berichtEJB.createBericht(bericht);
		return "viewConversation.faces?faces-redirect=true&amp;ontvanger="+bericht.getOntvanger().getId();
	}
	
}
