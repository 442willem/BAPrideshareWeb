package be.kuleuven.gent.project.jsf.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.data.Rit;
import be.kuleuven.gent.project.data.Route;
import be.kuleuven.gent.project.ejb.ProfielManagementEJBLocal;
import be.kuleuven.gent.project.ejb.RitManagementEJBLocal;
import be.kuleuven.gent.project.ejb.RouteManagementEJBLocal;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

@Named
@ViewScoped
public class ViewRouteController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2634080560770321272L;

	@EJB
	private RitManagementEJBLocal ritEJB;
	
	@EJB
	private RouteManagementEJBLocal routeEJB;
	
	@EJB
	private UserManagementEJBLocal userEJB;
	
	@EJB
	private ProfielManagementEJBLocal profielEJB;
	
	
	private Rit rit;
	private Route route;
	
	
	@PostConstruct
	public void init() {
		rit = new Rit();
		route = new Route();
	}
	
	
	public String requestRit(int routeid) {
		System.out.println(routeid);
		System.out.println("HIER");
		System.out.println(routeid + rit.getBeginpunt() + rit.getEindpunt());
//		System.out.println(Bp);
		System.out.println("MISS");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		
//		rit.setBeginpunt(Bp);
//		rit.setEindpunt(Ep);

		Profiel profielid = profielEJB.getProfiel();
		ritEJB.boekIn(rit, profielid, routeid);
		return "indexD.faces?faces-redirect=true&login=1";
	}
	
	public Rit getRit() {
		return rit;
	}
	
	public RouteManagementEJBLocal getRouteEJB() {
		return routeEJB;
	}
	
	public RitManagementEJBLocal getRitEJB() {
		return ritEJB;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void setRitEJB(RitManagementEJBLocal ritEJB) {
		this.ritEJB = ritEJB;
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

	public void setRit(Rit rit) {
		this.rit = rit;
	}
	public void findRoute() {
		route = routeEJB.findRoute(route.getId());
	}


	public Route getRoute() {
		return route;
	}


	public void setRoute(Route route) {
		this.route = route;
	}
	public String goToIndexD() {
		System.out.println("joooow");
		return "indexD.faces?faces-redirect=true&login=1&index=0";
	}
	

}
