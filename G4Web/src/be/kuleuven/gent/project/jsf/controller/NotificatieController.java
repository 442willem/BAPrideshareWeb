package be.kuleuven.gent.project.jsf.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import be.kuleuven.gent.project.data.*;
import be.kuleuven.gent.project.ejb.*;
import be.kuleuven.gent.project.ejb.ProfielManagementEJBLocal;

@Named
@ViewScoped
public class NotificatieController implements Serializable {
	private static final long serialVersionUID = 6731274724536164355L;
	
	@EJB
	private ProfielManagementEJBLocal profielEJB;

	@EJB
	private UserManagementEJBLocal userEJB;
	
	@EJB
	private RouteManagementEJBLocal routeEJB;
	
	@EJB
	private TussenstopManagementEJBLocal ritEJB;
	
	@EJB
	private NotificatieManagementLocal notificatieEJB;
	
	private Notificatie notificatie;
	
	@PostConstruct
	public void init() {
		setNotificatie(new Notificatie());
	}

	public Notificatie getNotificatie() {
		return notificatie;
	}

	public void setNotificatie(Notificatie notificatie) {
		this.notificatie = notificatie;
	}	
	public List<Notificatie> findNotificaties(){
		return notificatieEJB.findNotificaties(profielEJB.getProfiel().getId());
	}
	
	public void makeNotificatie(String type, int routeId, int ritId) {
		notificatie.setType(type);
		Date date = new Date();
		notificatie.setTijdstip(new Timestamp(date.getTime()));
		switch(type) {
		case "betaling":
			notificatie.setRit(ritEJB.findRit(ritId));
			notificatieEJB.createNotificatie(notificatie);
			break;
		case "review":
			notificatieEJB.createNotificatie(notificatie);
			break;
		case "routeHerinnering":
			notificatie.setRoute(routeEJB.findRoute(routeId));
			notificatieEJB.createNotificatie(notificatie);
			break;
		case "ritHerinnering":
			notificatie.setRit(ritEJB.findRit(ritId));
			notificatieEJB.createNotificatie(notificatie);
			break;
		case "ritAccepted":
			notificatie.setRit(ritEJB.findRit(ritId));
			notificatieEJB.createNotificatie(notificatie);
			break;
		case "ritChange":
			notificatie.setRit(ritEJB.findRit(ritId));
			notificatieEJB.createNotificatie(notificatie);
			break;
		default:
			notificatieEJB.createNotificatie(notificatie);
		}
	}
}
