package be.kuleuven.gent.project.jsf.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import be.kuleuven.gent.project.data.*;
import be.kuleuven.gent.project.ejb.*;

@Named
@SessionScoped
public class RouteController implements Serializable {
	private static final long serialVersionUID = -3737221385235612830L;
	@EJB
	private RouteManagementEJBLocal routeEJB;
	
	@EJB
	private UserManagementEJBLocal userEJB;
	
	private Route route;
	
	private String queryVertrek;
	private String queryEinde;
	private Timestamp queryVertrektijd;
	private Timestamp queryEindetijd;
	
	@PostConstruct
	public void init(){
		route = new Route();
		queryVertrek= null;
		queryEinde= null;
		queryVertrektijd= null;
		queryEindetijd= null;
	}
	
	public String createRoute() {
		routeEJB.createRoute(route);
		return "indexD.faces?faces-redirect=true&login=1";
	}
		
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	
	public RouteManagementEJBLocal getRouteEJB() {
		return routeEJB;
	}

	public void setRouteEJB(RouteManagementEJBLocal routeEJB) {
		this.routeEJB = routeEJB;
	}

	public UserManagementEJBLocal getUserEJB() {
		return userEJB;
	}

	public void setUserEJB(UserManagementEJBLocal userEJB) {
		this.userEJB = userEJB;
	}

	public String getQueryVertrek() {
		return queryVertrek;
	}

	public void setQueryVertrek(String queryVertrek) {
		this.queryVertrek = queryVertrek;
	}

	public String getQueryEinde() {
		return queryEinde;
	}

	public void setQueryEinde(String queryEinde) {
		this.queryEinde = queryEinde;
	}

	public Timestamp getQueryVertrektijd() {
		return queryVertrektijd;
	}

	public void setQueryVertrektijd(Timestamp queryVertrektijd) {
		this.queryVertrektijd = queryVertrektijd;
	}

	public Timestamp getQueryEindetijd() {
		return queryEindetijd;
	}

	public void setQueryEindetijd(Timestamp queryEindetijd) {
		this.queryEindetijd = queryEindetijd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getAantalRoutes() {
		return routeEJB.getAantalRoutes();
	}
	public List<Route> getRoutes() {
		return routeEJB.findRoutes(queryVertrek,queryEinde,queryVertrektijd,queryEindetijd);
	}
	public List<Route> findAllRoutes() {
		return routeEJB.findAllRoutes();
	}
	public String filterRoutes() {
		return "index.faces?faces-redirect=true";
	}
}
