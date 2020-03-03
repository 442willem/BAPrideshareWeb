package be.kuleuven.gent.project.jsf.controller;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import be.kuleuven.gent.project.data.*;
import be.kuleuven.gent.project.ejb.*;

@Named
@ViewScoped
public class RouteController implements Serializable {
	private static final long serialVersionUID = -3737221385235612830L;
	@EJB
	private RouteManagementEJBLocal routeEJB;
	
	@EJB
	private UserManagementEJBLocal userEJB;
	
	private Route route;
	private Route queryRoute;
	
	@PostConstruct
	public void init(){
		route = new Route();
		queryRoute = new Route();
	}
	
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public Route getQueryRoute() {
		return queryRoute;
	}
	public void setQueryRoute(Route queryRoute) {
		this.queryRoute = queryRoute;
	}	
	
	public int getAantalRoutes() {
		return routeEJB.getAantalRoutes();
	}
	public List<Route> getRoutes() {
		return routeEJB.findRoutes(queryRoute);
	}
	public List<Route> findAllRoutes() {
		return routeEJB.findAllRoutes();
	}
	public void filterRoutes() {
		
	}
}
