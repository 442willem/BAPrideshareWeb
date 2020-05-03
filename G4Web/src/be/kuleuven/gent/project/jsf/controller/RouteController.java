package be.kuleuven.gent.project.jsf.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.*;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
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
	
	@EJB
	private NotificatieManagementLocal notificatieEJB;
	
	private Route route;
	private Tussenstop rit = new Tussenstop();

	private String queryVertrek;
	private String queryEinde;
	private Date queryVertrektijd;
	private Date queryEindetijd;

	private int indexRoutes;


	@PostConstruct
	public void init(){
		route = new Route();
		queryVertrek=null;
		queryEinde=null;
		queryVertrektijd=null;
		queryEindetijd=null;
		System.out.println("Postconstruct");
	}

	public String createRoute() {
		routeEJB.createRoute(route);

		return "indexD.faces?faces-redirect=true&login=1&index=0";
	}

	public RitManagementEJBLocal getRitEJB() {
		return ritEJB;
	}

	public void setRitEJB(RitManagementEJBLocal ritEJB) {
		this.ritEJB = ritEJB;
	}


	public Tussenstop getRit() {
		return rit;
	}

	public void setRit(Tussenstop rit) {
		this.rit = rit;
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
	
	public int getIndexRoutes() {
		return indexRoutes;
	}

	public void setIndexRoutes(int indexRoutes) {
		this.indexRoutes = indexRoutes;
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

	public long getAantalRoutes() {
		return routeEJB.getAantalRoutes();
	}

	//alle routes worden opgeslaan en de initiele index wordt geplaatst op 0
	public List<Route> getRoutes() {
		List<Route> routes=new ArrayList<Route>();
		Timestamp eindetijd=null, vertrektijd;
		if(queryEindetijd!=null)eindetijd = new Timestamp(queryEindetijd.getTime());
		else eindetijd=null;
		if(queryVertrektijd!=null)vertrektijd=new Timestamp(queryVertrektijd.getTime());
		else vertrektijd=null;
		List <Route> alleRoutes = routeEJB.findRoutes(queryVertrek,queryEinde,vertrektijd,eindetijd);
		
		System.out.println("index:"+indexRoutes);
		for(int i=0;i<5;i++) {
			if(indexRoutes+i<alleRoutes.size()) {
				routes.add(alleRoutes.get(indexRoutes+i));
			}
		}
		//methode om de 5 volgende routes terug te krijgen
		return routes;
	}

	//methode om 5 routes te tonen als er op de knop NEXT word gedrukt
	public String volgendeRoutes(){
		indexRoutes+=5;
		System.out.println("count:"+routeEJB.getAantalRoutes()+"index:"+indexRoutes);
		if(indexRoutes>routeEJB.getAantalRoutes())indexRoutes-=5;
		System.out.println("volgende: "+indexRoutes);
		return "index.faces?faces-redirect=true&index="+indexRoutes;
	}

	// methode om 5 routes te tonen als er op de knop BACK wordt gedrukt
	public String vorigeRoutes(){
		indexRoutes-=5;
		if(indexRoutes<0)indexRoutes=0;
		System.out.println("vorige: "+indexRoutes);
		return "index.faces?faces-redirect=true&index="+indexRoutes;
	}
	//methode om 5 routes te tonen als er op de knop NEXT word gedrukt
	public String volgendeRoutesD(){
		indexRoutes+=5;
		if(indexRoutes>routeEJB.getAantalRoutes())indexRoutes-=5;
		System.out.println("volgende: "+indexRoutes);
		return "indexD.faces?faces-redirect=true&login=1&index="+indexRoutes;
	}

	// methode om 5 routes te tonen als er op de knop BACK wordt gedrukt
	public String vorigeRoutesD(){
		indexRoutes-=5;
		if(indexRoutes<0)indexRoutes=0;
		System.out.println("vorige: "+indexRoutes);
		return "indexD.faces?faces-redirect=true&login=1&index="+indexRoutes;
	}

	public List<Route> findAllRoutes() {
		return routeEJB.findAllRoutes();
	}
	public List<Route> findByBestuurder(){		
		return routeEJB.findBestuurderRoute(profielEJB.getProfiel().getId());
	}
	public String filterRoutes() {
		return "index.faces?faces-redirect=true&index="+indexRoutes;
	}
	public String keurRitGoed(int ritId) {
		ritEJB.keurRitGoed(ritId);
		Notificatie n = new Notificatie("ritAccepted");
		n.setProfiel(profielEJB.getProfiel());
		n.setRit(ritEJB.findRit(ritId));
		notificatieEJB.createNotificatie(n);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		return "viewPassengers.faces?faces-redirect=true&route="+route.getId();
	}
	public String keurRitAf(int ritId) {
		System.out.println("routecontroller"+ritId);
		ritEJB.keurRitAf(ritId);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		return "viewPassengers.faces?faces-redirect=true&route="+route.getId();
	}
	public void setTussenstops() {
		System.out.println("routeId= "+route.getId());
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
}
