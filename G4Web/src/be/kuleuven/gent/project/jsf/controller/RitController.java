package be.kuleuven.gent.project.jsf.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import be.kuleuven.gent.project.*;
import be.kuleuven.gent.project.data.Rit;
import be.kuleuven.gent.project.ejb.RitManagementEJBLocal;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;


@Named
@ViewScoped
public class RitController implements Serializable {
	private static final long serialVersionUID = -3737221385235612830L;
	@EJB
	private RitManagementEJBLocal ritEJB;
	
	@EJB
	private UserManagementEJBLocal userEJB;
	
	private Rit rit = new Rit();
	
	
	
	
	public Rit getRit() {
		return rit;
	}
	public void setRit(Rit rit) {
		this.rit = rit;
	}
	
	
	
}
