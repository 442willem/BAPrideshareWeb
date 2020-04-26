package be.kuleuven.gent.project.jsf.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import be.kuleuven.gent.project.*;
import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.data.Rit;
import be.kuleuven.gent.project.data.Route;
import be.kuleuven.gent.project.ejb.ProfielManagementEJBLocal;
import be.kuleuven.gent.project.ejb.RitManagementEJBLocal;
import be.kuleuven.gent.project.ejb.RouteManagementEJBLocal;
import be.kuleuven.gent.project.ejb.TussenstopManagementEJBLocal;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;


@Named
@RequestScoped
public class RitController implements Serializable {

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
	private int routeid;
	
	@PostConstruct
	public void init() {
		rit = new Rit();
		rit.setBeginpunt("iets");
		rit.setVertrektijdDate(new Date("Tue Mar 24 2020 14:12:00 GMT+0100 (Midden-Europese standaardtijd"));
	}
	
	
	public String requestRit(int routeid, String Bp, String Ep) {
		System.out.println(routeid);
		System.out.println("HIER");
		System.out.println(Bp);
		System.out.println("MISS");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		
		rit.setBeginpunt(Bp);
		rit.setEindpunt(Ep);

		Profiel profielid = profielEJB.getProfiel();
		rit.setPassagier(profielid);
		ritEJB.boekIn(rit,  routeid);
		return "indexD.faces?faces-redirect=true&login=1";
	}
	
	public Rit getRit() {
		return rit;
	}
	
	public int getRouteid() {
		return routeid;
	}


	public void setRouteid(int routeid) {
		this.routeid = routeid;
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
	public void findRit() {
		rit = ritEJB.findRit(rit.getId());
	}
	public int getAantalRitten() {
		return ritEJB.getAantalRitten();
	}
	public List<Rit> findAllRitten() {
		return ritEJB.findAllRitten();
	}
	public List<Route> findByPassagier(){		
		return ritEJB.findPassagierRit(profielEJB.getProfiel().getId());
	}
	public void betaalRit() {
		ritEJB.betaalRit(rit.getId());
	}
}
