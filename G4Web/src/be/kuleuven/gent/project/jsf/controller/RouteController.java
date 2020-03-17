package be.kuleuven.gent.project.jsf.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@ViewScoped
public class RouteController implements Serializable {
	private static final long serialVersionUID = -3737221385235612830L;
	@EJB
	private RouteManagementEJBLocal routeEJB;
	
	@EJB
	private UserManagementEJBLocal userEJB;
	
	@EJB
	private ProfielManagementEJBLocal profielEJB;
	
	@EJB
	private RitManagementEJBLocal ritEJB;

	private Route route;
	
	private String queryVertrek;
	private String queryEinde;
	private Date queryVertrektijd;
	private Date queryEindetijd;
	
	@PostConstruct
	public void init(){
		route = new Route();
		queryVertrek=null;
		queryEinde=null;
		queryVertrektijd=null;
		queryEindetijd=null;
	}
	
	public String createRoute() {
		routeEJB.createRoute(route);
		return "indexD.faces?faces-redirect=true&login=1";
	}
	public String requestRit(String routeid) {
		System.out.println(routeid);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		int Rid= Integer.parseInt(routeid);
		Profiel profielid = profielEJB.getProfiel();
		routeEJB.boekIn(profielid, Rid);
		return "indexD.faces?faces-redirect=true&login=1";
	}
	public void findRoute() {
		route = routeEJB.findRoute(route.getId());
	}
	public List<Rit> findRitten(){
		return ritEJB.findRitten(route.getId());
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
	public ProfielManagementEJBLocal getProfielEJB() {
		return profielEJB;
	}

	public void setProfielEJB(ProfielManagementEJBLocal profielEJB) {
		this.profielEJB = profielEJB;
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

	public Date getQueryVertrektijd() {
		return queryVertrektijd;
	}

	public void setQueryVertrektijd(Date queryVertrektijd) {
		this.queryVertrektijd = queryVertrektijd;
	}

	public Date getQueryEindetijd() {
		return queryEindetijd;
	}

	public void setQueryEindetijd(Date queryEindetijd) {
		this.queryEindetijd = queryEindetijd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getAantalRoutes() {
		return routeEJB.getAantalRoutes();
	}
	public List<Route> getRoutes() {
		Timestamp eindetijd=null, vertrektijd;
		if(queryEindetijd!=null)eindetijd = new Timestamp(queryEindetijd.getTime());
		else eindetijd=null;
		if(queryVertrektijd!=null)vertrektijd=new Timestamp(queryVertrektijd.getTime());
		else vertrektijd=null;
		return routeEJB.findRoutes(queryVertrek,queryEinde,vertrektijd,eindetijd);
	}
	public List<Route> findAllRoutes() {
		return routeEJB.findAllRoutes();
	}
	public List<Route> findByBestuurder(){		
		return routeEJB.findBestuurderRoute(profielEJB.getProfiel().getId());
	}
	public String filterRoutes() {
		return "index.faces?faces-redirect=true";
	}
}
