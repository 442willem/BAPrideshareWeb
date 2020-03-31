package be.kuleuven.gent.project.jsf.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;


@Named
@ViewScoped
public class RitController implements Serializable {
	private static final long serialVersionUID = -3737221385235612830L;
	@EJB
	private RitManagementEJBLocal ritEJB;
	
	@EJB
	private RouteManagementEJBLocal routeEJB;
	
	@EJB
	private UserManagementEJBLocal userEJB;
	
	@EJB
	private ProfielManagementEJBLocal profielEJB;
	
	private Rit rit;
	
	@PostConstruct
	public void init() {
		rit = new Rit();
	}
	
	
	public String requestRit(String routeid) {
		System.out.println(routeid);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		int Rid= Integer.parseInt(routeid);
		Profiel profielid = profielEJB.getProfiel();
		ritEJB.boekIn(rit, profielid, Rid);
		return "indexD.faces?faces-redirect=true&login=1";
	}
	
	public Rit getRit() {
		return rit;
	}
	public void setRit(Rit rit) {
		this.rit = rit;
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
}
