package be.kuleuven.gent.project.jsf.controller;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import be.kuleuven.gent.project.data.Route;
import be.kuleuven.gent.project.data.Tussenstop;
import be.kuleuven.gent.project.ejb.ProfielManagementEJBLocal;
import be.kuleuven.gent.project.ejb.RouteManagementEJBLocal;
import be.kuleuven.gent.project.ejb.TussenstopManagementEJBLocal;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

@Named
@SessionScoped
public class TirController implements Serializable {
	@EJB
	private TussenstopManagementEJBLocal tirejb;
	@EJB
	private UserManagementEJBLocal userEJB;
	
	@EJB
	private RouteManagementEJBLocal routeEJB;
	
	@EJB
	private ProfielManagementEJBLocal profielEJB;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4588093485332857196L;

	private Tussenstop tir ;
	private Route route;

	
	@PostConstruct
	public void init() {
		tir = new Tussenstop();
		route = new Route();
	
	}
	
	public void findRit() {
		tir = tirejb.findRit(tir.getId());
		route = routeEJB.findRoute(tir.getRoute().getId());
	}
	
	public String test(int Routeid, String test) {
		System.out.println(test);
		System.out.println("KIEKEBOE");
		tir.setRoute(routeEJB.findRoute(Routeid));
		tir.setVertrektijd(new Date(test));
		tirejb.createTir(tir);
		return "indexD.faces?faces-redirect=true&login=1";
	}
	
	
	
	public String[] zoekGeaccepteerdeTussenstops(int routeID){
		return routeEJB.zoekGeaccepteerdeTussenstops(route.getId());
	}
	
	public void findRoute() {
		route = routeEJB.findRoute(route.getId());
	}
	

	public TussenstopManagementEJBLocal getTirejb() {
		return tirejb;
	}

	public void setTirejb(TussenstopManagementEJBLocal tirejb) {
		this.tirejb = tirejb;
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

	

	public Tussenstop getTir() {
		return tir;
	}


	public void setTir(Tussenstop tir) {
		this.tir = tir;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public RouteManagementEJBLocal getRouteEJB() {
		return routeEJB;
	}


	public void setRouteEJB(RouteManagementEJBLocal routeEJB) {
		this.routeEJB = routeEJB;
	}


	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
	public void setTussenstops() {
		String[] array=routeEJB.zoekGeaccepteerdeTussenstops(route.getId());
		System.out.println("Aantal tussenstops:"+array.length);
		if(array.length>0) {
		StringBuilder sb = new StringBuilder("");
		for(int a=0; a<array.length;a++)sb.append(array[a]+"|");
		sb.deleteCharAt(sb.length()-1);
		route.setTussenstops(sb.toString());
		}else route.setTussenstops("");
		System.out.println("Tussenstops geset:" + route.getTussenstops());
	}	
	public String keurRitGoed(int ritId) {
		tirejb.keurRitGoed(ritId);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		return "viewPassengers.faces?faces-redirect=true&route="+route.getId();
	}
}
